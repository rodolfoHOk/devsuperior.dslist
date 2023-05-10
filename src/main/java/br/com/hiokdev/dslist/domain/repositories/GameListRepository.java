package br.com.hiokdev.dslist.domain.repositories;

import br.com.hiokdev.dslist.domain.entities.GameList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameListRepository extends JpaRepository<GameList, Long> {
}
