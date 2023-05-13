package br.com.hiokdev.dslist.api.openapi;

import br.com.hiokdev.dslist.api.dto.GameDTO;
import br.com.hiokdev.dslist.api.dto.GameInputDTO;
import br.com.hiokdev.dslist.api.dto.GameMinDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Games")
public interface GameControllerOpenAPI {
  @Operation(summary = "List games")
  List<GameMinDTO> findAll();

  @Operation(summary = "Get game by ID")
  GameDTO findById(Long id);

  @Operation(summary = "Register new game")
  GameDTO create(GameInputDTO gameInputDTO);

  @Operation(summary = "Remove game")
  void delete(Long id);

  @Operation(summary = "Update game")
  GameDTO update(Long gameId, GameInputDTO gameInputDTO);

}
