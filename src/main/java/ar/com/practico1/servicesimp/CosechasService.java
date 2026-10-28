package ar.com.practico1.servicesimp;

import ar.com.practico1.dao.ICosechasDao;
import ar.com.practico1.entities.Cosechas;
import ar.com.practico1.service.ICosechaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CosechasService implements ICosechaService {
    @Autowired
    private ICosechasDao entityDao;

    public List<Cosechas> getAll() {
        return entityDao.findAll(Sort.by(Sort.Direction.ASC, "fecha"));
    }

    public Cosechas get(Integer id) {
        return entityDao.findById(id).orElse(null);
    }

    public Page<Cosechas> findAll(Pageable pageable) {
        return entityDao.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "rendimiento")));
    }

    @Transactional
    public void save(Cosechas entity) {
        entityDao.save(entity);
    }

    @Transactional
    public String delete(Cosechas entity) {
        try {
            entityDao.delete(entity);
            return null;
        } catch (Exception e) {
            return e.getMessage().toString();
        }
    }
}
