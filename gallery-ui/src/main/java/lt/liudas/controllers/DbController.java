package lt.liudas.controllers;

import lt.liudas.entities.ImageEntity;
import lt.liudas.repositories.ImageRepository;
import lt.liudas.services.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DbController {

    private DbService dbService;
    private ImageRepository imageRepositoryImpl;

    @Autowired
    public DbController(DbService dbService, ImageRepository imageRepositoryImpl) {
        this.dbService = dbService;
        this.imageRepositoryImpl = imageRepositoryImpl;
    }

    // Get All Images
    @GetMapping("/images")
    public List<ImageEntity> getAllImages() {
        return dbService.getAllImages();
    }

    // Create a new ImageEntity
    @PostMapping("/images")
//    public ImageEntity saveImage(@Valid @RequestBody ImageEntity image) {
    public ImageEntity saveImage(@RequestParam("file") MultipartFile file) throws IOException {
        return dbService.saveImage(file);
    }

    // Get a Single ImageEntity
    @GetMapping("/images/{id}")
    public ImageEntity getImageById(@PathVariable(value = "id") Long imageId) {
        return dbService.getImageById(imageId);
    }

    // Update a ImageEntity
    @PutMapping("/images/{id}")
    public ImageEntity updateImage(@PathVariable(value = "id") Long imageId, @Valid @RequestBody ImageEntity imageDetails) {
        return dbService.updateImage(imageId, imageDetails);
    }

    // Delete a ImageEntity
    @DeleteMapping("/images/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable(value = "id") Long imageId) {
        return dbService.deleteImage(imageId);
    }
}