package lt.liudas.dao;

import lt.liudas.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity findByName(String name);
}
