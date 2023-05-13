package br.com.hiokdev.dslist.api.dto;

import br.com.hiokdev.dslist.domain.entities.Game;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class GameDTO {

  private Long id;
  private String title;
  private Integer year;
  private String genre;
  private String platforms;
  private Double score;
  private String imgUrl;
  private String shortDescription;
  private String longDescription;

  public GameDTO(Game entity) {
    BeanUtils.copyProperties(entity, this);
  }

}
