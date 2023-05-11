package br.com.hiokdev.dslist.api.dto;

import br.com.hiokdev.dslist.domain.entities.GameList;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameListInputDTO {

  @NotBlank
  @Size(min = 2)
  private String name;

  public static GameList toEntity (GameListInputDTO gameListInputDTO) {
    var gameList = new GameList();
    gameList.setName(gameListInputDTO.getName());
    return gameList;
  }

}
