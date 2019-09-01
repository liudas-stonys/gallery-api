package lt.liudas.services;

import lt.liudas.entities.ImageEntity;
import lt.liudas.exceptions.ResourceNotFoundException;
import lt.liudas.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
public class DbService {

    private ImageRepository imageRepositoryImpl;

    @Autowired
    public DbService(ImageRepository imageRepositoryImpl) {
        this.imageRepositoryImpl = imageRepositoryImpl;
    }

    // Get All Images
    public List<ImageEntity> getAllImages() {
        return imageRepositoryImpl.findAll();
    }

    // Create a new ImageEntity
//    public ImageEntity saveImage(ImageEntity image) {
    public ImageEntity saveImage(MultipartFile file) throws IOException {
        ImageEntity image = new ImageEntity();
        image.setData(file.getBytes());
        image.setTitle("Best image ever forever fuck");
        return imageRepositoryImpl.save(image);
    }

    // Get a Single ImageEntity
    public ImageEntity getImageById(Long imageId) {
        return imageRepositoryImpl.findById(imageId).orElseThrow(() -> new ResourceNotFoundException("ImageEntity", "id", imageId));
    }

    // Update a ImageEntity
    public ImageEntity updateImage(Long imageId, ImageEntity imageDetails) {

        ImageEntity image = imageRepositoryImpl.findById(imageId).orElseThrow(() -> new ResourceNotFoundException(
                "ImageEntity", "id", imageId));

        image.setTitle(imageDetails.getTitle());
        image.setData(imageDetails.getData());

        ImageEntity updatedImage = imageRepositoryImpl.save(image);
        return updatedImage;
    }

    // Delete a ImageEntity
    public ResponseEntity<?> deleteImage(Long imageId) {
        ImageEntity image = imageRepositoryImpl.findById(imageId).orElseThrow(() -> new ResourceNotFoundException(
                "ImageEntity", "id", imageId));

        imageRepositoryImpl.delete(image);

        return ResponseEntity.ok().build();
    }
}
