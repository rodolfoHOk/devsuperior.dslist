package br.com.hiokdev.dslist.domain.services;

import br.com.hiokdev.dslist.domain.entities.GameList;
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

}
