package lt.liudas.repositoriesDAO;

import lt.liudas.entities.ImageEntity;
import lt.liudas.entities.TagEntity;
import org.aspectj.weaver.ast.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Long> {
//    List<TagEntity> findAllByImageEntity_Title(String title);

//    @Query(value ="select ie from ImageEntity ie join ie.tags t where t.id = ?1")
//    List<ImageEntity> findImageEntityByTagId(Long id);
}