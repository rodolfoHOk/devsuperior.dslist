package br.com.hiokdev.dslist.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_belonging")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Belonging {

  @EmbeddedId
  @EqualsAndHashCode.Include
  private BelongingPK id = new BelongingPK();

  @Column(nullable = false)
  private Integer position;

  public Belonging(Game game, GameList list, Integer position) {
    this.id.setGame(game);
    this.id.setList(list);
    this.position = position;
  }

}
