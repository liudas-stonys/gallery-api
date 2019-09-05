package lt.liudas.services;

import lt.liudas.entities.ImageEntity;
import lt.liudas.entities.ImageThumbnailEntity;
import lt.liudas.entities.TagEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DbService {
//    List<ImageEntity> findImageEntityByTagId(Long id);
//    void findAllImageEntitiesWithTags();
//    List<TagEntity> findAllByImageEntity_Title(String title);
//    List<ImageEntity> findImageEntityByTagId(List<TagEntity> tags);

    List<ImageEntity> findImageEntityByTagName(String name);
    List<TagEntity> getAllTags();
    List<ImageThumbnailEntity> getAllImagesThumbnails();
    List<ImageEntity> getAllImages();
    ImageEntity saveImage(MultipartFile file) throws IOException;
    ImageEntity getImageById(Long imageId);
    ImageEntity updateImage(Long imageId, ImageEntity imageDetails);
    ResponseEntity<?> deleteImage(Long imageId);
}
