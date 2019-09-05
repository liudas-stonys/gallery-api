package lt.liudas.repositoriesDAO;

import lt.liudas.entities.ImageEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
public class ImageRepositoryCustomImpl implements ImageRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    public List<ImageEntity> getImageEntityByTag(String tag) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        //Using criteria builder you can build your criteria queries.
        return null;
    }
}
