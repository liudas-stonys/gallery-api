package lt.liudas.controllers;

import lt.liudas.entities.ImageEntity;
import lt.liudas.entities.ImageThumbnailEntity;
import lt.liudas.entities.TagEntity;
import lt.liudas.services.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class DbController {

    private DbService dbServiceImpl;

    @Autowired
    public DbController(DbService dbServiceImpl) {
        this.dbServiceImpl = dbServiceImpl;
    }

//    @GetMapping("/images/tag/id/{id}")
//    public List<ImageEntity> findImageEntityByTagId(@PathVariable(value = "id") Long id) {
//        return dbServiceImpl.findImageEntityByTagId(id);
//    }
    
    // Get All Images by Tag Name
    @GetMapping("/images/tag/name/{name}")
    public List<ImageEntity> findImageEntityByTagName(@PathVariable(value = "name") String name) {
        return dbServiceImpl.findImageEntityByTagName(name);
    }

    // Get All Tags
    @GetMapping("/tags")
    public List<TagEntity> getAllTags() { return dbServiceImpl.getAllTags(); }

    // Get All Images
    @GetMapping("/images/thumbnails")
    public List<ImageThumbnailEntity> getAllImagesThumbnails() {
        return dbServiceImpl.getAllImagesThumbnails();
    }

    // Get All Images
    @GetMapping("/images")
    public List<ImageEntity> getAllImages() {
        return dbServiceImpl.getAllImages();
    }

    // Create a new ImageEntity
    @PostMapping("/images")
//    public ImageEntity saveImage(@Valid @RequestBody ImageEntity image) {
    public ImageEntity saveImage(@RequestParam("file") MultipartFile file) throws IOException {
        return dbServiceImpl.saveImage(file);
    }

    // Get a Single ImageEntity
    @GetMapping("/images/{id}")
    public ImageEntity getImageById(@PathVariable(value = "id") Long imageId) {
        return dbServiceImpl.getImageById(imageId);
    }

    // Update a ImageEntity
    @PutMapping("/images/{id}")
    public ImageEntity updateImage(@PathVariable(value = "id") Long imageId, @Valid @RequestBody ImageEntity imageDetails) {
        return dbServiceImpl.updateImage(imageId, imageDetails);
    }

    // Delete a ImageEntity
    @DeleteMapping("/images/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable(value = "id") Long imageId) {
        return dbServiceImpl.deleteImage(imageId);
    }
}