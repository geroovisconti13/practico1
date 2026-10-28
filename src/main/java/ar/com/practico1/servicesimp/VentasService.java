package ar.com.practico1.servicesimp;

import ar.com.practico1.dao.IVentasDao;
import ar.com.practico1.entities.Cosechas;
import ar.com.practico1.entities.Ventas;
import ar.com.practico1.service.IVentasService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentasService implements IVentasService {

    @Autowired
    private IVentasDao entityDao;

    public List<Ventas> getAll() {
        return entityDao.findAll(Sort.by(Sort.Direction.ASC, "fecha"));
    }

    public Ventas get(Integer id) {
        return entityDao.findById(id).orElse(null);
    }

    public Page<Ventas> findAll(Pageable pageable) {
        return entityDao.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "cantvendida")));
    }

    @Transactional
    public void save(Ventas entity) {
        entityDao.save(entity);
    }

    @Transactional
    public String delete(Ventas entity) {
        try {
            entityDao.delete(entity);
            return null;
        } catch (Exception e) {
            return e.getMessage().toString();
        }
    }
}