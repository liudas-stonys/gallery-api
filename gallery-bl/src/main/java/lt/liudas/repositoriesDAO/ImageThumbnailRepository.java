package lt.liudas.repositoriesDAO;

import lt.liudas.entities.ImageThumbnailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageThumbnailRepository extends JpaRepository<ImageThumbnailEntity, Long> {
}
