package lt.liudas.dao;

import lt.liudas.entities.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagDao extends JpaRepository<TagEntity, Long> {
    TagEntity findOneByName(String name);
}