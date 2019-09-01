package lt.liudas.sbs.dao;

import lt.liudas.sbs.models.UserDaoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends CrudRepository<UserDaoEntity, Integer> {
    @Override
    <S extends UserDaoEntity> S save(S s);

    UserDaoEntity findByUsername(String username);

    @Override
    List<UserDaoEntity> findAll();
}