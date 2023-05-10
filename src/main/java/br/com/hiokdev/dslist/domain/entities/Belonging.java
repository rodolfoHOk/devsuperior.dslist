package br.com.hiokdev.dslist.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_belonging")
public class Belonging {

  @EmbeddedId
  private BelongingPK id = new BelongingPK();

  @Column(nullable = false)
  private Integer position;

  public Belonging() {
  }

  public Belonging(Game game, GameList list, Integer position) {
    this.id.setGame(game);
    this.id.setList(list);
    this.position = position;
  }

  public BelongingPK getId() {
    return id;
  }

  public void setId(BelongingPK id) {
    this.id = id;
  }

  public Integer getPosition() {
    return position;
  }

  public void setPosition(Integer position) {
    this.position = position;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Belonging belonging = (Belonging) o;

    return id.equals(belonging.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

}
