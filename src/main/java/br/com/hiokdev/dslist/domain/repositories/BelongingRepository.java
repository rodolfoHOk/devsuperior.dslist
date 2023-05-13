package br.com.hiokdev.dslist.domain.repositories;

import br.com.hiokdev.dslist.domain.entities.Belonging;
import br.com.hiokdev.dslist.domain.entities.BelongingPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BelongingRepository extends JpaRepository<Belonging, BelongingPK> {

  @Query(nativeQuery = true, value = """
    SELECT * FROM tb_belonging
    WHERE game_id = :gameId
    """)
  List<Belonging> findAllByGameId(Long gameId);

}
