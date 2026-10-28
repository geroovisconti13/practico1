package ar.com.practico1.dao;

import ar.com.practico1.entities.Cultivos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICultivosDao extends JpaRepository<Cultivos, Integer> {
    @Query("select c from Cultivos c where c.descrip like ?1")
    public List<Cultivos> findByDescrip(String descrip);
}
