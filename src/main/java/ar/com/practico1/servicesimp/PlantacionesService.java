package ar.com.practico1.servicesimp;

import ar.com.practico1.dao.IPlantacionesDao;
import ar.com.practico1.entities.Plantaciones;
import ar.com.practico1.service.IPlantacionesService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PlantacionesService implements IPlantacionesService {

    @Autowired
    private IPlantacionesDao entityDao;

    public List<Plantaciones> getAll() { return entityDao.findAll(Sort.by(Sort.Direction.ASC, "periodo"));}

    public Plantaciones get(Integer id) {
        return entityDao.findById(id).orElse( null);
    }

    public Page<Plantaciones> findAll(Pageable pageable) {
        return entityDao.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "periodo")));
    }


    @Transactional
    public void save (Plantaciones entity) {entityDao.save(entity); }

    @Transactional
    public String delete(Plantaciones entity) {
        try {
            entityDao.delete(entity);
            return null;
        } catch (Exception e) {
            return e.getMessage().toString();
        }
    }

    public List<Plantaciones> getPlantacionesPeriodo(String periodo) {
        return entityDao.getPlantacionesPeriodo(periodo);
    }

    public List<Plantaciones> getPlantacionesBetweenfechas(LocalDate fecha, LocalDate fecha_cierre) {
        return List.of();
    }

}
