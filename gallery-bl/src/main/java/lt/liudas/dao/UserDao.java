package lt.liudas.dao;

import lt.liudas.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}