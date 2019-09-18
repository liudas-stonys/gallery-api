package lt.liudas.controllers;

import lt.liudas.entities.CategoryEntity;
import lt.liudas.entities.ImageEntity;
import lt.liudas.entities.ImageFullSize;
import lt.liudas.entities.TagEntity;
import lt.liudas.helpers.MainHelper;
import lt.liudas.services.DbServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DbController {

    @Autowired
    private DbServiceImpl dbServiceImpl;

    @GetMapping("/images/search")
    public List<ImageEntity> searchImages(@RequestParam List<String> data) {
        MainHelper.printList(data);
        return dbServiceImpl.searchImages(data);
    }

    @GetMapping("/images/categories")
    public List<CategoryEntity> getAllCategories() {
        return dbServiceImpl.getAllCategories();
    }

    @GetMapping("/images/tags")
    public List<TagEntity> getAllTags() {
        return dbServiceImpl.getAllTags();
    }

    @GetMapping("/images/tags/{name}")
    public List<ImageEntity> findImageEntityByTagName(@PathVariable(value = "name") String name) {
        return dbServiceImpl.findImageEntityByTagName(name);
    }

    @GetMapping("/images/fullsize")
    public List<ImageFullSize> getAllImagesFullSize() {
        return dbServiceImpl.getAllImagesFullSize();
    }

    @GetMapping("/images")
    public List<ImageEntity> getAllImages() {
        return dbServiceImpl.getAllImages();
    }

    @PostMapping("/images")
    public ImageEntity saveImage(@RequestParam("title") String title, @RequestParam("file") MultipartFile file, @RequestParam("categories") List<String> categories, @RequestParam("tags") List<String> tags) throws IOException {
        return dbServiceImpl.saveImage(title, file, categories, tags);
    }

    @GetMapping("/images/{id}")
    public ImageEntity getImageById(@PathVariable(value = "id") Long imageId) {
        return dbServiceImpl.getImageById(imageId);
    }

    @GetMapping("/images/fullsize/{id}")
    public ImageFullSize getImageFullSizeById(@PathVariable(value = "id") Long imageId) {
        return dbServiceImpl.getImageFullSizeById(imageId);
    }

    @PutMapping("/images/{id}")
    public ImageEntity updateImage(@PathVariable(value = "id") Long imageId, @Valid @RequestBody ImageEntity imageDetails) {
        return dbServiceImpl.updateImage(imageId, imageDetails);
    }

    @DeleteMapping("/images/{id}")
    public List<ImageEntity> deleteImage(@PathVariable(value = "id") Long imageId) {
        return dbServiceImpl.deleteImage(imageId);
    }
}