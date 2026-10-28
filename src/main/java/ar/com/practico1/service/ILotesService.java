package ar.com.practico1.service;

import ar.com.practico1.entities.Lotes;

import java.util.List;

public interface ILotesService {
    List<Lotes> getAll();
    List<Lotes> findByDescrip(String descrip);
    Lotes get(Integer id);
    void save (Lotes entity);
    String delete(Lotes entity);
}
