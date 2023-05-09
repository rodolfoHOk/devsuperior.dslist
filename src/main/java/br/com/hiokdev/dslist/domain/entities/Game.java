package br.com.hiokdev.dslist.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "tb_game")
public class Game {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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

  public Game() {
  }

  public Game(Long id, String title, Integer year, String genre, String platforms, Double score, String imgUrl,
              String shortDescription, String longDescription) {
    this.id = id;
    this.title = title;
    this.year = year;
    this.genre = genre;
    this.platforms = platforms;
    this.score = score;
    this.imgUrl = imgUrl;
    this.shortDescription = shortDescription;
    this.longDescription = longDescription;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public String getPlatforms() {
    return platforms;
  }

  public void setPlatforms(String platforms) {
    this.platforms = platforms;
  }

  public Double getScore() {
    return score;
  }

  public void setScore(Double score) {
    this.score = score;
  }

  public String getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }

  public String getShortDescription() {
    return shortDescription;
  }

  public void setShortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
  }

  public String getLongDescription() {
    return longDescription;
  }

  public void setLongDescription(String longDescription) {
    this.longDescription = longDescription;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Game game = (Game) o;

    return Objects.equals(id, game.id);
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }

}
