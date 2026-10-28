package ar.com.practico1.dao;

import ar.com.practico1.entities.Lotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ILotesDao extends JpaRepository<Lotes, Integer>{
    @Query("select c from Lotes c where c.descrip like ?1")
    public List<Lotes> findByDescrip(String descrip);
}
