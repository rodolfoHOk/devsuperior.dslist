package br.com.hiokdev.dslist.api.dto;

import br.com.hiokdev.dslist.domain.entities.Game;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameInputDTO {

  @NotNull
  @Positive
  private Long listId;

  @NotBlank
  @Size(min = 2, max = 100)
  private String title;

  @NotNull
  @Min(value = 1970)
  private Integer year;

  @NotBlank
  @Size(max = 80)
  private String genre;

  @NotBlank
  @Size(max = 80)
  private String platforms;

  private Double score;

  @NotBlank
  @Size(max = 255)
  private String imgUrl;

  @NotBlank
  private String shortDescription;

  @NotBlank
  private String longDescription;

  public static Game toEntity(GameInputDTO gameInputDTO) {
    return Game.builder()
      .title(gameInputDTO.getTitle())
      .year(gameInputDTO.getYear())
      .genre(gameInputDTO.getGenre())
      .platforms(gameInputDTO.getPlatforms())
      .score(gameInputDTO.getScore())
      .imgUrl(gameInputDTO.getImgUrl())
      .shortDescription(gameInputDTO.getShortDescription())
      .longDescription(gameInputDTO.getLongDescription())
      .build();
  }

}
