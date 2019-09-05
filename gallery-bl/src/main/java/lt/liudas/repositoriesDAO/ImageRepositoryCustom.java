package lt.liudas.repositoriesDAO;

import lt.liudas.entities.ImageEntity;

import java.util.List;

public interface ImageRepositoryCustom {
    List<ImageEntity> getImageEntityByTag(String tag);
}
