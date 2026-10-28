package ar.com.practico1.dao;

import ar.com.practico1.entities.Compradores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICompradoresDao extends JpaRepository<Compradores, Integer> {
    @Query("select c from Compradores c where c.cuit like ?1")
    public List<Compradores> findByCuit(String cuit);

}
