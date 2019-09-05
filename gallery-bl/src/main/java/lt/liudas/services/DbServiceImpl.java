package lt.liudas.services;

import lt.liudas.entities.ImageEntity;
import lt.liudas.entities.ImageThumbnailEntity;
import lt.liudas.entities.TagEntity;
import lt.liudas.exceptions.ResourceNotFoundException;
import lt.liudas.helpers.MainHelper;
import lt.liudas.repositoriesDAO.ImageRepository;
import lt.liudas.repositoriesDAO.ImageThumbnailRepository;
import lt.liudas.repositoriesDAO.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;
import java.util.List;

@Service
public class DbServiceImpl implements DbService {

//    @PersistenceContext
    private EntityManager em;

    private ImageRepository imageRepositoryImpl;
    private ImageThumbnailRepository imageThumbnailRepositoryImpl;
    private TagRepository tagRepositoryImpl;

    @Autowired
    public DbServiceImpl(EntityManager em, ImageRepository imageRepositoryImpl, ImageThumbnailRepository imageThumbnailRepositoryImpl, TagRepository tagRepositoryImpl) {
        this.em = em;
        this.imageRepositoryImpl = imageRepositoryImpl;
        this.imageThumbnailRepositoryImpl = imageThumbnailRepositoryImpl;
        this.tagRepositoryImpl = tagRepositoryImpl;
    }

//    public List<ImageEntity> findImageEntityByTagId(Long id) {
//        return imageRepositoryImpl.findImageEntityByTagId(id);
//    }



//    @Override
//    public void findAllImageEntitiesWithTags() {
//        System.out.println("-- find images with tags --");
//        EntityManager em = emf.createEntityManager();
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<ImageEntity> query = cb.createQuery(ImageEntity.class);
//        Root<ImageEntity> imageEntity = query.from(ImageEntity.class);
//
//        imageEntity.join(ImageEntity.getTags());
//
//        query.select(employee).distinct(true);
//        TypedQuery<ImageEntity> typedQuery = em.createQuery(query);
//        typedQuery.getResultList().forEach(System.out::println);
//    }

    // Get All Tags By Image Title
//    @Override
//    public List<TagEntity> findAllByImageEntity_Title(String title) {
//        return tagRepositoryImpl.findAllByImageEntity_Title(title);
//    }

    // Get All Images By Tag
//    @Override
//    public List<ImageEntity> findImageEntityByTagId(List<TagEntity> tags) {
//        return imageRepositoryImpl.findImageEntityByTagId(tags);
//    }

//    public List<ImageEntity> getAllImagesByTag(String tag) {
//        EntityManager em = new JPAUtil().getEntityManager();
//        Session session = em.unwrap(Session.class);
//        Criteria c = session.createCriteria(Name.class);
//
//        return null;
//    }





    // Get All Images by Tag Name
    public List<ImageEntity> findImageEntityByTagName(String name) {
        return imageRepositoryImpl.findImageEntityByTagName(name);
    }

    // Get All Tags
    public List<TagEntity> getAllTags() { return tagRepositoryImpl.findAll(); }

    // Get All Images Thumbnails
    public List<ImageThumbnailEntity> getAllImagesThumbnails() {
        return imageThumbnailRepositoryImpl.findAll();
    }

    // Get All Images
    public List<ImageEntity> getAllImages() {
        return imageRepositoryImpl.findAll();
    }

    // Create a new ImageEntity
//    public ImageEntity saveImage(ImageEntity image) {
    public ImageEntity saveImage(MultipartFile file) throws IOException {

//        ImageEntity image = new ImageEntity();
//        image.setData(file.getBytes());
//        image.setTitle("Best image ever forever fuck");

        byte[] imgThumb = MainHelper.createThumbnailFromMultipartFile(file, 300).toByteArray();

        imageThumbnailRepositoryImpl.save(new ImageThumbnailEntity(file.getName(), imgThumb, file.getContentType(), (long) imgThumb.length));

        return imageRepositoryImpl.save(new ImageEntity(file.getName(), file.getBytes(), file.getContentType(), file.getSize()));
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
