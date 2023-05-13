package br.com.hiokdev.dslist.api.dto;

import br.com.hiokdev.dslist.domain.entities.GameList;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GameListDTO {

  private Long id;
  private String name;

  public GameListDTO(GameList entity) {
    this.id = entity.getId();
    this.name = entity.getName();
  }

}
