package br.com.hiokdev.dslist.domain.repositories;

import br.com.hiokdev.dslist.domain.entities.Belonging;
import br.com.hiokdev.dslist.domain.entities.BelongingPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BelongingRepository extends JpaRepository<Belonging, BelongingPK> {
}
