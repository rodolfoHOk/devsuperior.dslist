package br.com.hiokdev.dslist.domain.services;

import br.com.hiokdev.dslist.domain.entities.Belonging;
import br.com.hiokdev.dslist.domain.entities.BelongingPK;
import br.com.hiokdev.dslist.domain.entities.Game;
import br.com.hiokdev.dslist.domain.exceptions.ResourceNotFoundException;
import br.com.hiokdev.dslist.domain.exceptions.ValidationException;
import br.com.hiokdev.dslist.domain.projections.GameMinProjection;
import br.com.hiokdev.dslist.domain.repositories.BelongingRepository;
import br.com.hiokdev.dslist.domain.repositories.GameListRepository;
import br.com.hiokdev.dslist.domain.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameService {

  private final GameRepository gameRepository;
  private final GameListRepository gameListRepository;
  private final BelongingRepository belongingRepository;

  @Autowired
  public GameService(GameRepository gameRepository, GameListRepository gameListRepository,
                     BelongingRepository belongingRepository) {
    this.gameRepository = gameRepository;
    this.gameListRepository = gameListRepository;
    this.belongingRepository = belongingRepository;
  }

  @Transactional(readOnly = true)
  public List<Game> findAll() {
    return gameRepository.findAll();
  }

  @Transactional(readOnly = true)
  public Game findById(Long id) {
    return gameRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Game not found"));
  }

  @Transactional(readOnly = true)
  public List<GameMinProjection> findAllByList(Long listId) {
    return gameRepository.searchByList(listId);
  }

  @Transactional
  public Game create(Long gameListId, Game game) {
    game.validate();

    var gameList = gameListRepository.findById(gameListId)
      .orElseThrow(() -> new ValidationException("Game list not exists"));

    if (gameRepository.existsByTitle(game.getTitle())) {
      throw new ValidationException("Game name already exists");
    }
    var savedGame = gameRepository.save(game);

    var position = gameRepository.countByList(gameListId);
    var belonging = new Belonging(savedGame, gameList, position.intValue());
    belongingRepository.save(belonging);

    return savedGame;
  }

  @Transactional
  public void delete(Long gameId) {
    if (!gameRepository.existsById(gameId)) {
      throw new ValidationException("Game not exists");
    }

    List<Belonging> belongings = belongingRepository.findAllByGameId(gameId);
    belongings.forEach(belonging -> {
      BelongingPK belongingId = new BelongingPK(belonging.getId().getGame(), belonging.getId().getList());
      belongingRepository.deleteById(belongingId);
    });

    gameRepository.deleteById(gameId);
  }

}
