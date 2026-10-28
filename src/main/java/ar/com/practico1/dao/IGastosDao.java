package ar.com.practico1.dao;

import ar.com.practico1.entities.Gastos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IGastosDao extends JpaRepository<Gastos ,Integer> {
    @Query("select c from Gastos c where c.concepto like ?1")
    public List<Gastos> findByConcepto(String concepto);
}
