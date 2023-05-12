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

}
