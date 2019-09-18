package lt.liudas.services;

import lt.liudas.entities.CategoryEntity;
import lt.liudas.entities.ImageEntity;
import lt.liudas.entities.ImageFullSize;
import lt.liudas.entities.TagEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DbService {
    List<ImageEntity> searchImages(List<String> data);

    List<CategoryEntity> getAllCategories();

    List<ImageEntity> findImageEntityByTagName(String name);

    List<TagEntity> getAllTags();

    List<ImageFullSize> getAllImagesFullSize();

    List<ImageEntity> getAllImages();

    ImageEntity saveImage(String title, MultipartFile file, List<String> categories, List<String> tags) throws IOException;

    ImageEntity getImageById(Long imageId);

    ImageFullSize getImageFullSizeById(Long imageId);

    ImageEntity updateImage(Long imageId, ImageEntity imageDetails);

    List<ImageEntity> deleteImage(Long imageId);
}
