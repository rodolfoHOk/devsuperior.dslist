package br.com.hiokdev.dslist.api.controllers;

import br.com.hiokdev.dslist.api.dto.GameListDTO;
import br.com.hiokdev.dslist.api.dto.GameListInputDTO;
import br.com.hiokdev.dslist.api.dto.GameMinDTO;
import br.com.hiokdev.dslist.api.dto.ReplacementDTO;
import br.com.hiokdev.dslist.domain.services.GameListService;
import br.com.hiokdev.dslist.domain.services.GameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/lists")
public class GameListController {

  private final GameListService gameListService;
  private final GameService gameService;

  @Autowired
  public GameListController(GameListService gameListService, GameService gameService) {
    this.gameListService = gameListService;
    this.gameService = gameService;
  }

  @GetMapping
  public List<GameListDTO> findAll() {
    var list = gameListService.findAll();
    return list.stream().map(GameListDTO::new).toList();
  }

  @GetMapping(value = "/{id}/games")
  public List<GameMinDTO> findGamesByList(@PathVariable(value = "id") Long listId) {
    var games = gameService.findAllByList(listId);
    return games.stream().map(GameMinDTO::new).toList();
  }

  @PostMapping(value = "/{id}/replacement")
  public void move(@PathVariable(name = "id") Long listId, @Valid @RequestBody ReplacementDTO body) {
    gameListService.move(listId, body.getSourceIndex(), body.getDestinationIndex());
  }

  @PostMapping
  public GameListDTO create(@Valid @RequestBody GameListInputDTO gameListInputDTO) {
    var gameListInput = GameListInputDTO.toEntity(gameListInputDTO);
    var gameListCreated = gameListService.create(gameListInput);
    return new GameListDTO(gameListCreated);
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    gameListService.delete(id);
  }

}
