package br.com.hiokdev.dslist.domain.repositories;

import br.com.hiokdev.dslist.domain.entities.Game;
import br.com.hiokdev.dslist.domain.projections.GameMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

  @Query(nativeQuery = true, value = """
		SELECT tb_game.id, tb_game.title, tb_game.game_year AS gameYear, tb_game.img_url AS imgUrl,
		tb_game.short_description AS shortDescription, tb_belonging.position
		FROM tb_game
		INNER JOIN tb_belonging ON tb_game.id = tb_belonging.game_id
		WHERE tb_belonging.list_id = :listId
		ORDER BY tb_belonging.position
		""")
	List<GameMinProjection> searchByList(Long listId);

	@Query(nativeQuery = true, value = """
		SELECT COUNT(*)
		FROM tb_game tg
		INNER JOIN tb_belonging tb
		ON tg.id = tb.game_id
		WHERE tb.list_id = :listId
		""")
	Long countByList(Long listId);

	Boolean existsByTitle(String title);

}
