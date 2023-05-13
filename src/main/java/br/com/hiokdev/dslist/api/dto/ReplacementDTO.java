package br.com.hiokdev.dslist.api.dto;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplacementDTO {

  @PositiveOrZero
  private Integer sourceIndex;

  @PositiveOrZero
  private Integer destinationIndex;

}
