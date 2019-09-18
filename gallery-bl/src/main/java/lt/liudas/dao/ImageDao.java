package lt.liudas.dao;

import lt.liudas.entities.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageDao extends JpaRepository<ImageEntity, Long> {
    @Query(value = "select ie from ImageEntity ie join ie.tags t where t.name = ?1")
    List<ImageEntity> findImageEntityByTagName(String name);
}