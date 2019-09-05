package lt.liudas.services;

import lt.liudas.entities.ImageEntity;
import lt.liudas.entities.TagEntity;

import java.io.IOException;
import java.util.Set;

public interface DbSeederService {
    ImageEntity createImageEntity(String title, String filePath, Set<TagEntity>tags) throws IOException;
}
