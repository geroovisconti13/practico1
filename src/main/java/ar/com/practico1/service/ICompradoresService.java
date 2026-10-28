package ar.com.practico1.service;

import ar.com.practico1.entities.Compradores;

import java.util.List;

public interface ICompradoresService {
    List<Compradores> getAll();
    List<Compradores> findByCuit(String cuit);
    List<Compradores> findById(Integer id);
    Compradores get(Integer id);
    void save (Compradores entity);
    String delete(Compradores entity);
}
