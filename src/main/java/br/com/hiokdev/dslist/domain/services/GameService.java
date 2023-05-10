package br.com.hiokdev.dslist.domain.services;

import br.com.hiokdev.dslist.domain.entities.Game;
import br.com.hiokdev.dslist.domain.exceptions.ResourceNotFoundException;
import br.com.hiokdev.dslist.domain.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameService {

  private final GameRepository gameRepository;

  @Autowired
  public GameService(GameRepository gameRepository) {
    this.gameRepository = gameRepository;
  }

  @Transactional(readOnly = true)
  public List<Game> findAll() {
    return gameRepository.findAll();
  }

  @Transactional(readOnly = true)
  public Game findById(Long id) {
    return gameRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Game not found"));
  }

}
