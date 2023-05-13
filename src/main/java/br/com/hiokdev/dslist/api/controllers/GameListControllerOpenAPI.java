package br.com.hiokdev.dslist.api.controllers;

import br.com.hiokdev.dslist.api.dto.GameListDTO;
import br.com.hiokdev.dslist.api.dto.GameListInputDTO;
import br.com.hiokdev.dslist.api.dto.GameMinDTO;
import br.com.hiokdev.dslist.api.dto.ReplacementDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Games lists")
public interface GameListControllerOpenAPI {

  @Operation(summary = "List games lists")
  List<GameListDTO> findAll();

  @Operation(summary = "List games of games list")
  List<GameMinDTO> findGamesByList(Long listId);

  @Operation(summary = "Move game position in list")
  void move(Long listId, ReplacementDTO body);

  @Operation(summary = "Create new games list")
  GameListDTO create(GameListInputDTO gameListInputDTO);

  @Operation(summary = "Remove games list")
  void delete(Long id);

  @Operation(summary = "Update games list")
  GameListDTO update(Long id, GameListInputDTO gameListInputDTO);

}
