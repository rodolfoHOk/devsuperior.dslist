package br.com.hiokdev.dslist.api.controllers;

import br.com.hiokdev.dslist.api.dto.GameDTO;
import br.com.hiokdev.dslist.api.dto.GameInputDTO;
import br.com.hiokdev.dslist.api.dto.GameMinDTO;
import br.com.hiokdev.dslist.domain.services.GameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/games")
public class GameController {

  private final GameService gameService;

  @Autowired
  public GameController(GameService gameService) {
    this.gameService = gameService;
  }

  @GetMapping
  public List<GameMinDTO> findAll() {
    var games = gameService.findAll();
    return games.stream().map(GameMinDTO::new).toList();
  }

  @GetMapping(value = "/{id}")
  public GameDTO findById(@PathVariable Long id) {
    var game = gameService.findById(id);
    return new GameDTO(game);
  }

  @PostMapping
  public GameDTO create(@Valid @RequestBody GameInputDTO gameInputDTO) {
    var gameInput = GameInputDTO.toEntity(gameInputDTO);
    var savedGame = gameService.create(gameInputDTO.getListId(), gameInput);
    return new GameDTO(savedGame);
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    gameService.delete(id);
  }

  @PutMapping(value = "/{id}")
  public GameDTO update(@PathVariable(name = "id") Long gameId, @Valid @RequestBody GameInputDTO gameInputDTO) {
    var gameInput = GameInputDTO.toEntity(gameInputDTO);
    var updatedGame = gameService.update(gameInputDTO.getListId(), gameId, gameInput);
    return new GameDTO(updatedGame);
  }

}
