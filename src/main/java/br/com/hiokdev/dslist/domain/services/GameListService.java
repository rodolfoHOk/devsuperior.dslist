package br.com.hiokdev.dslist.domain.services;

import br.com.hiokdev.dslist.domain.entities.GameList;
import br.com.hiokdev.dslist.domain.exceptions.ValidationException;
import br.com.hiokdev.dslist.domain.repositories.GameListRepository;
import br.com.hiokdev.dslist.domain.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameListService {

  private final GameListRepository gameListRepository;
  private final GameRepository gameRepository;

  @Autowired
  public GameListService(GameListRepository gameListRepository, GameRepository gameRepository) {
    this.gameListRepository = gameListRepository;
    this.gameRepository = gameRepository;
  }

  @Transactional(readOnly = true)
  public List<GameList> findAll() {
    return gameListRepository.findAll();
  }

  @Transactional
  public void move(Long gameListId, int sourceIndex, int destinationIndex) {
    var list = gameRepository.searchByList(gameListId);

    if (sourceIndex == destinationIndex) {
      throw new ValidationException("Source index must be different from destination index");
    }
    if (sourceIndex < 0 || sourceIndex >= list.size()) {
      throw new ValidationException("Invalid source index");
    }
    if (destinationIndex < 0 || destinationIndex >= list.size()) {
      throw new ValidationException("Invalid destination index");
    }

    var gameToMove = list.remove(sourceIndex);
    list.add(destinationIndex, gameToMove);

    int min = Math.min(sourceIndex, destinationIndex);
    int max = Math.max(sourceIndex, destinationIndex);
    for (int i = min; i <= max; i++) {
      gameListRepository.updateBelongingPosition(gameListId, list.get(i).getId(), i);
    }
  }

  @Transactional(readOnly = true)
  public Boolean existsByName(String name) {
    return gameListRepository.existsByName(name);
  }

  @Transactional
  public GameList create(GameList gameList) {
    if (existsByName(gameList.getName())) {
      throw new ValidationException("Game list already exists");
    }
    if (gameList.getName().length() < 2) {
      throw new ValidationException("Game list name must be at least two characters");
    }
    return gameListRepository.save(gameList);
  }

  @Transactional
  public void delete(Long gameListId) {
    if (!gameListRepository.existsById(gameListId)) {
      throw new ValidationException("Game list not exists");
    }
    if (gameRepository.countByList(gameListId) > 0) {
      throw new ValidationException("Game list in use");
    }
    gameListRepository.deleteById(gameListId);
  }

  @Transactional
  public GameList update(Long gameListId, GameList gameListInput) {
    var gameToUpdate = gameListRepository.findById(gameListId)
      .orElseThrow(() -> new ValidationException("Game list not found"));
    if (!gameToUpdate.getName().equals(gameListInput.getName())
      && existsByName(gameListInput.getName())) {
      throw new ValidationException("Game list already exists");
    }
    gameToUpdate.setName(gameListInput.getName());
    return gameListRepository.save(gameToUpdate);
  }

}
