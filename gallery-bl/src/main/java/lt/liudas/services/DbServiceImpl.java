package lt.liudas.services;

import lt.liudas.dao.*;
import lt.liudas.entities.*;
import lt.liudas.exceptions.ResourceNotFoundException;
import lt.liudas.helpers.MainHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DbServiceImpl implements DbService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private UserDao userDaoImpl;
    @Autowired
    private ImageDao imageDaoImpl;
    @Autowired
    private ImageFullSizeDao imageFullSizeDaoImpl;
    @Autowired
    private CategoryDao categoryDaoImpl;
    @Autowired
    private TagDao tagDaoImpl;

    public List<ImageEntity> searchImages(List<String> data) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ImageEntity> query = cb.createQuery(ImageEntity.class);
        // ... FROM ...
        Root<ImageEntity> imagesTable = query.from(ImageEntity.class);
        // Outer join
        Join<ImageEntity, CategoryEntity> categories = imagesTable.join("categories", JoinType.LEFT);
        Join<ImageEntity, TagEntity> tags = imagesTable.join("tags", JoinType.LEFT);
        // Eliminates duplicates
        query.select(imagesTable).distinct(true); // query.distinct(true);
        List<Predicate> finalPredicateList = new ArrayList<>();
        data.forEach(item -> {
            finalPredicateList.add(cb.like(imagesTable.get("title"), "%" + item + "%"));
            finalPredicateList.add(cb.like(imagesTable.get("description"), "%" + item + "%"));
            finalPredicateList.add(cb.like(categories.get("name"), "%" + item + "%"));
            finalPredicateList.add(cb.like(tags.get("name"), "%" + item + "%"));
        });
        // ... WHERE ...
        Predicate finalPredicate = finalPredicateList.get(0);
        for (Predicate predicate : finalPredicateList) {
            finalPredicate = cb.or(finalPredicate, predicate);
        }
        query.where(finalPredicate);
        return em.createQuery(query).getResultList();
    }

    public List<UserEntity> getAllUsers() {
        return userDaoImpl.findAll();
    }

    public List<CategoryEntity> getAllCategories() {
        return categoryDaoImpl.findAll();
    }

    public List<ImageEntity> findImageEntityByTagName(String name) {
        return imageDaoImpl.findImageEntityByTagName(name);
    }

    public List<TagEntity> getAllTags() {
        return tagDaoImpl.findAll();
    }

    public List<ImageFullSize> getAllImagesFullSize() {
        return imageFullSizeDaoImpl.findAll();
    }

    public List<ImageEntity> getAllImages() {
        return imageDaoImpl.findAll();
    }

    // Create a new ImageEntity
    public ImageEntity saveImage(String title, String description, List<String> categories, List<String> tags, MultipartFile file) throws IOException {
        Set<CategoryEntity> categoryEntities = new HashSet<>();
        Set<TagEntity> tagEntities = new HashSet<>();
        categories.forEach(cat -> {
            CategoryEntity categoryEntityFromDb = categoryDaoImpl.findByName(cat);
            if (categoryEntityFromDb != null) {
                categoryEntities.add(categoryEntityFromDb);
            } else {
                categoryEntities.add(new CategoryEntity(cat));
            }
        });
        tags.forEach(tag -> {
            TagEntity tagEntityFromDb = tagDaoImpl.findByName(tag);
            if (tagEntityFromDb != null) {
                tagEntities.add(tagEntityFromDb);
            } else {
                tagEntities.add(new TagEntity(tag));
            }
        });

        ImageFullSize imageFullSize = imageFullSizeDaoImpl.save(new ImageFullSize(title, file.getContentType(), file.getBytes(), file.getSize()));
        byte[] imgThumb = MainHelper.createThumbnailFromMultipartFile(file, 300);
        return imageDaoImpl.save(new ImageEntity(imageFullSize.getId(), title, file.getContentType(), imgThumb, (long) imgThumb.length, description, categoryEntities, tagEntities));
    }

    public ImageEntity getImageById(Long imageId) {
        return imageDaoImpl.findById(imageId).orElseThrow(() -> new ResourceNotFoundException("ImageEntity", "id", imageId));
    }

    public ImageFullSize getImageFullSizeById(Long imageId) {
        return imageFullSizeDaoImpl.findById(imageId).orElseThrow(() -> new ResourceNotFoundException("ImageEntity", "id", imageId));
    }

    public ImageEntity updateImage(Long imageId, ImageEntity imageDetails) {
        ImageEntity image = imageDaoImpl.findById(imageId).orElseThrow(() -> new ResourceNotFoundException(
                "ImageEntity", "id", imageId));
        image.setTitle(imageDetails.getTitle());
        image.setData(imageDetails.getData());
        return image;
    }

    public List<ImageEntity> deleteImage(Long imageId) {
        ImageEntity image = imageDaoImpl.findById(imageId).orElseThrow(() -> new ResourceNotFoundException("ImageEntity", "id", imageId));
        imageDaoImpl.delete(image);
        return imageDaoImpl.findAll();
    }
}
