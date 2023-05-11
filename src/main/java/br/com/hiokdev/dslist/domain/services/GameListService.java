package br.com.hiokdev.dslist.domain.services;

import br.com.hiokdev.dslist.domain.entities.GameList;
import br.com.hiokdev.dslist.domain.exceptions.ValidationException;
import br.com.hiokdev.dslist.domain.repositories.GameListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameListService {

  private final GameListRepository gameListRepository;

  @Autowired
  public GameListService(GameListRepository gameListRepository) {
    this.gameListRepository = gameListRepository;
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

}
