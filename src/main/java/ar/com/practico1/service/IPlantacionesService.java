package ar.com.practico1.service;

import ar.com.practico1.entities.Plantaciones;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface IPlantacionesService {
    List<Plantaciones> getAll();
    Page<Plantaciones> findAll(Pageable pageable);
    Plantaciones get(Integer id);
    void save (Plantaciones entity);
    String delete(Plantaciones entity);
    List<Plantaciones> getPlantacionesPeriodo(String periodo);
    List<Plantaciones> getPlantacionesBetweenfechas(LocalDate fecha, LocalDate fecha_cierre);
}
