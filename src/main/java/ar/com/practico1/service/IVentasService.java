package ar.com.practico1.service;

import ar.com.practico1.entities.Ventas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IVentasService {
    List<Ventas> getAll();
    Page<Ventas> findAll(Pageable pageable);
    Ventas get(Integer id);
    void save(Ventas entity);
    String delete(Ventas entity);
}
