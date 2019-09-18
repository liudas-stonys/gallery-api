package lt.liudas.services;

import lt.liudas.entities.CategoryEntity;
import lt.liudas.entities.ImageEntity;
import lt.liudas.entities.ImageFullSize;
import lt.liudas.entities.TagEntity;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public interface DbSeederService {
    ImageFullSize createImageFullSizeEntity(String title, String filePath) throws IOException;

    ImageEntity createImageEntity(Long idImageFullSize, String title, String filePath, Set<CategoryEntity> categories, Set<TagEntity> tags) throws IOException;

    byte[] resize(File file, String mime) throws IOException;
}
