package ar.com.practico1.servicesimp;

import ar.com.practico1.dao.ILotesDao;
import ar.com.practico1.entities.Lotes;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import ar.com.practico1.service.ILotesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotesService implements ILotesService {

    @Autowired
    private ILotesDao entityDao;

    public List<Lotes> getAll() { return entityDao.findAll(Sort.by(Sort.Direction.ASC, "descrip"));}

    public List<Lotes> findByDescrip(String descrip){ return entityDao.findByDescrip( "%"+ descrip + "%");}

    public Lotes get(Integer id) {
        return entityDao.findById(id).orElse( null);
    }

    @Transactional
    public void save (Lotes entity) {entityDao.save(entity); }

    @Transactional
    public String delete(Lotes entity) {
        try {
            entityDao.delete(entity);
            return null;
        } catch (Exception e) {
            return e.getMessage().toString();
        }
    }

}
