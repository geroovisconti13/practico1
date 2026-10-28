package ar.com.practico1.service;

import ar.com.practico1.entities.Cultivos;

import java.util.List;

public interface ICultivosService {
    List<Cultivos> getAll();
    List<Cultivos> findByDescrip(String descrip);
    Cultivos get(Integer id);
    void save (Cultivos entity);
    String delete(Cultivos entity);
}
