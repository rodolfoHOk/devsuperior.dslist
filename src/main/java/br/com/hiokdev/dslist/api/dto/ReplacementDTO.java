package br.com.hiokdev.dslist.api.dto;

import jakarta.validation.constraints.PositiveOrZero;

public class ReplacementDTO {

  @PositiveOrZero
  private Integer sourceIndex;

  @PositiveOrZero
  private Integer destinationIndex;

  public Integer getSourceIndex() {
    return sourceIndex;
  }

  public void setSourceIndex(Integer sourceIndex) {
    this.sourceIndex = sourceIndex;
  }

  public Integer getDestinationIndex() {
    return destinationIndex;
  }

  public void setDestinationIndex(Integer destinationIndex) {
    this.destinationIndex = destinationIndex;
  }

}
