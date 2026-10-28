package ar.com.practico1.service;

import ar.com.practico1.entities.Cosechas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICosechaService {
    List<Cosechas> getAll();
    Page<Cosechas> findAll(Pageable pageable);
    Cosechas get(Integer id);
    void save(Cosechas entity);
    String delete(Cosechas entity);
}
