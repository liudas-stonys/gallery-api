package lt.liudas.sbs.dao;

import lt.liudas.sbs.models.UserDaoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<UserDaoEntity, Integer> {
    @Override
    <S extends UserDaoEntity> S save(S s);

    UserDaoEntity findByUsername(String username);
}