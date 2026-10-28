package ar.com.practico1.service;

import ar.com.practico1.entities.Gastos;
import ar.com.practico1.entities.Plantaciones;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IGastosService {
    List<Gastos> getAll();
    List<Gastos> findByConcepto(String concepto);
    Page<Gastos> findAll(Pageable pageable);
    Gastos get(Integer id);
    void save (Gastos entity);
    String delete(Gastos entity);
}
