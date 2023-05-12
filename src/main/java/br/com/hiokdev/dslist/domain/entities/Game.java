package br.com.hiokdev.dslist.domain.entities;

import br.com.hiokdev.dslist.domain.exceptions.ValidationException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;


@Entity
@Table(name = "tb_game")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Game {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Long id;

  @Column(nullable = false, unique = true, length = 100)
  private String title;

  @Column(name = "game_year", nullable = false)
  private Integer year;

  @Column(nullable = false, length = 80)
  private String genre;

  @Column(nullable = false, length = 80)
  private String platforms;

  private Double score;

  @Column(name="img_url", nullable = false)
  private String imgUrl;

  @Column(name = "short_description", nullable = false, columnDefinition = "TEXT")
  private String shortDescription;

  @Column(name = "long_description", nullable = false, columnDefinition = "TEXT")
  private String longDescription;
  
  public void validate() {
    if (this.title.length() < 2) {
      throw new ValidationException("Title must be at least two characters");
    }
    if (this.title.length() > 100) {
      throw new ValidationException("Title must have a maximum of 100 characters");
    }
    if (this.year < 1970) {
      throw new ValidationException("Year must be greater than 1970");
    }
    if (this.year > Year.now().getValue()) {
      throw new ValidationException("Year must be less than or equal to " + Year.now().getValue());
    }
    if (this.genre.length() == 0) {
      throw new ValidationException("Genre cannot be blank");
    }
    if (this.genre.length() > 80) {
      throw new ValidationException("Genre must have a maximum of 80 characters");
    }
    if (this.platforms.length() == 0) {
      throw new ValidationException("Platforms cannot be blank");
    }
    if (this.platforms.length() > 80) {
      throw new ValidationException("Platforms must have a maximum of 80 characters");
    }
    if (this.score != null && this.getScore() < 0.0) {
      throw new ValidationException("Score must be positive or zero");
    }
    if (this.imgUrl.length() == 0) {
      throw new ValidationException("ImgUrl cannot be blank");
    }
    if (this.imgUrl.length() > 255) {
      throw new ValidationException("ImgUrl must have a maximum of 255 characters");
    }
    if (this.shortDescription.length() == 0) {
      throw new ValidationException("Short description cannot be blank");
    }
    if (this.longDescription.length() == 0) {
      throw new ValidationException("Long description cannot be blank");
    }
  }

}
