package br.com.hiokdev.dslist.api.controllers;

import br.com.hiokdev.dslist.api.dto.GameListDTO;
import br.com.hiokdev.dslist.api.dto.GameListInputDTO;
import br.com.hiokdev.dslist.api.dto.GameMinDTO;
import br.com.hiokdev.dslist.api.dto.ReplacementDTO;
import br.com.hiokdev.dslist.domain.services.GameListService;
import br.com.hiokdev.dslist.domain.services.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequestMapping(value = "/lists")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GameListController implements GameListControllerOpenAPI {

  private final GameListService gameListService;
  private final GameService gameService;

  @Override
  @GetMapping
  public List<GameListDTO> findAll() {
    var list = gameListService.findAll();
    return list.stream().map(GameListDTO::new).toList();
  }

  @Override
  @GetMapping(value = "/{id}/games")
  public List<GameMinDTO> findGamesByList(@PathVariable(value = "id") Long listId) {
    var games = gameService.findAllByList(listId);
    return games.stream().map(GameMinDTO::new).toList();
  }

  @Override
  @PostMapping(value = "/{id}/replacement")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void move(@PathVariable(name = "id") Long listId, @Valid @RequestBody ReplacementDTO body) {
    gameListService.move(listId, body.getSourceIndex(), body.getDestinationIndex());
  }

  @Override
  @PostMapping
  public GameListDTO create(@RequestBody GameListInputDTO gameListInputDTO) {
    var gameListInput = GameListInputDTO.toEntity(gameListInputDTO);
    var createdGameList = gameListService.create(gameListInput);
    return new GameListDTO(createdGameList);
  }

  @Override
  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    gameListService.delete(id);
  }

  @Override
  @PutMapping(value = "/{id}")
  public GameListDTO update(@PathVariable Long id, @Valid @RequestBody GameListInputDTO gameListInputDTO) {
    var gameListInput = GameListInputDTO.toEntity(gameListInputDTO);
    var updatedGameList = gameListService.update(id, gameListInput);
    return new GameListDTO(updatedGameList);
  }

}
