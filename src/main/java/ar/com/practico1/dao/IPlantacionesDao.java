package ar.com.practico1.dao;

import ar.com.practico1.entities.Plantaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface IPlantacionesDao extends JpaRepository<Plantaciones, Integer> {
    @Query("select c from Plantaciones c where c.periodo = ?1 order by c.fecha")
    public List<Plantaciones> getPlantacionesPeriodo(String periodo);

    @Query ("select c from Plantaciones c where c.fecha between ?1 and ?2")
    public List<Plantaciones> getPlantacionesBetweenfechas(LocalDate fecha, LocalDate fecha_cierre);
}
