package ar.com.practico1.servicesimp;

import java.util.List;

import ar.com.practico1.dao.ICompradoresDao;
import ar.com.practico1.entities.Compradores;
import ar.com.practico1.service.ICompradoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompradoresService implements ICompradoresService {

    @Autowired
    private ICompradoresDao entityDao;

    public List<Compradores> getAll() {
        return entityDao.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
    }

    public Page<Compradores> getAll(Pageable pageable) {
        return entityDao.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "descrip")));
    }

    public List<Compradores> findByCuit(String cuit) {
        return entityDao.findByCuit("%" + cuit + "%");
    }

    @Override
    public List<Compradores> findById(Integer id) {
        return List.of();
    }

    public Compradores get(Integer id) {
        return entityDao.findById(id).orElse(null);
    }

    @Transactional
    public void save(Compradores entity) {
        entityDao.save(entity);
    }

    @Transactional
    public String delete(Compradores entity) {
        try {
            entityDao.delete(entity);
            return null;
        } catch (Exception e) {
            return e.getMessage().toString();
        }
    }
}
