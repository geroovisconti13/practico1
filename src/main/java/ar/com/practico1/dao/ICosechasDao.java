package ar.com.practico1.dao;

import ar.com.practico1.entities.Cosechas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICosechasDao extends JpaRepository<Cosechas, Integer> {
    Page<Cosechas> findAll(Pageable pageable);
}

