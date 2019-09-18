package lt.liudas.dao;

import lt.liudas.entities.ImageFullSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageFullSizeDao extends JpaRepository<ImageFullSize, Long> {
}
