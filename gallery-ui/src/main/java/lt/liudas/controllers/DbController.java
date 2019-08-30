package lt.liudas.controllers;

import lt.liudas.entities.ImageEntity;
import lt.liudas.exceptions.ResourceNotFoundException;
import lt.liudas.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DbController {

    private ImageRepository imageRepository;

    @Autowired
    public DbController(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    // Get All Images
    @GetMapping("/images")
    public List<ImageEntity> getAllImages() {
        return imageRepository.findAll();
    }

    // Create a new ImageEntity
    @PostMapping("/images")
    public ImageEntity createImage(@Valid @RequestBody ImageEntity image) {
        return imageRepository.save(image);
    }

    // Get a Single ImageEntity
    @GetMapping("/images/{id}")
    public ImageEntity getImageById(@PathVariable(value = "id") Long imageId) {
        return imageRepository.findById(imageId).orElseThrow(() -> new ResourceNotFoundException("ImageEntity", "id", imageId));
    }

    // Update a ImageEntity
    @PutMapping("/images/{id}")
    public ImageEntity updateImage(@PathVariable(value = "id") Long imageId,
                                   @Valid @RequestBody ImageEntity imageDetails) {

        ImageEntity image = imageRepository.findById(imageId).orElseThrow(() -> new ResourceNotFoundException("ImageEntity", "id", imageId));

        image.setTitle(imageDetails.getTitle());
        image.setData(imageDetails.getData());

        ImageEntity updatedImage = imageRepository.save(image);
        return updatedImage;
    }

    // Delete a ImageEntity
    @DeleteMapping("/images/{id}")
    public ResponseEntity<?> deleteImageEntity(@PathVariable(value = "id") Long imageId) {
        ImageEntity image = imageRepository.findById(imageId).orElseThrow(() -> new ResourceNotFoundException("ImageEntity", "id", imageId));

        imageRepository.delete(image);

        return ResponseEntity.ok().build();
    }
}