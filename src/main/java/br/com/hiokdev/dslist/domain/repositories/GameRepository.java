package br.com.hiokdev.dslist.domain.repositories;

import br.com.hiokdev.dslist.domain.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
