package lt.liudas.services;

import lt.liudas.entities.ImageEntity;
import lt.liudas.entities.ImageThumbnailEntity;
import lt.liudas.entities.TagEntity;
import lt.liudas.repositoriesDAO.ImageRepository;
import lt.liudas.repositoriesDAO.ImageThumbnailRepository;
import lt.liudas.repositoriesDAO.TagRepository;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class DbSeederServiceImpl implements DbSeederService, CommandLineRunner {


    @PersistenceContext
    private EntityManager em;

    private ImageRepository imageRepositoryImpl;
    private ImageThumbnailRepository imageThumbnailRepositoryImpl;
    private TagRepository tagRepositoryImpl;

    @Autowired
    public DbSeederServiceImpl(ImageRepository imageRepositoryImpl, ImageThumbnailRepository imageThumbnailRepositoryImpl, TagRepository tagRepositoryImpl) {
        this.imageRepositoryImpl = imageRepositoryImpl;
        this.imageThumbnailRepositoryImpl = imageThumbnailRepositoryImpl;
        this.tagRepositoryImpl = tagRepositoryImpl;
    }

    //    @Override
    @Transactional
    public void run(String... args) throws Exception {
        String[] filePath = {
                "../gallery-bl/src/main/java/lt/liudas/assets/2.jpeg",
                "../gallery-bl/src/main/java/lt/liudas/assets/3.jpeg",
                "../gallery-bl/src/main/java/lt/liudas/assets/4.jpg",
                "../gallery-bl/src/main/java/lt/liudas/assets/5.jpg",
                "../gallery-bl/src/main/java/lt/liudas/assets/6.jpg",
                "../gallery-bl/src/main/java/lt/liudas/assets/7.jpg",
                "../gallery-bl/src/main/java/lt/liudas/assets/8.png",
                "../gallery-bl/src/main/java/lt/liudas/assets/9.jpg",
                "../gallery-bl/src/main/java/lt/liudas/assets/10.jpg",
                "../gallery-bl/src/main/java/lt/liudas/assets/1.jpeg",
                "../gallery-bl/src/main/java/lt/liudas/assets/2.jpeg",
                "../gallery-bl/src/main/java/lt/liudas/assets/3.jpeg"
        };
//        printElasticSearchInfo();

//        ImageEntity img4 = createImageEntity("../gallery-bl/src/main/java/lt/liudas/assets/4.jpg");
//        imageRepositoryImpl.save(img4);

//        ImageEntity img1 = new ImageEntity("img1", getImageData("../gallery-bl/src/main/java/lt/liudas/assets/1.jpeg"));
//        ImageEntity img2 = new ImageEntity("img2", getImageData("../gallery-bl/src/main/java/lt/liudas/assets/2.jpeg"));
//        ImageEntity img3 = new ImageEntity("img3", getImageData("../gallery-bl/src/main/java/lt/liudas/assets/3.jpeg"));
//
        List<ImageEntity> images = new ArrayList<>();
        List<ImageThumbnailEntity> imagesThumbnails = new ArrayList<>();

        Set<TagEntity> tags = new HashSet<>();
        tags.add(new TagEntity("Popular"));
        tags.add(new TagEntity("meow"));
        tags.add(new TagEntity("Aha!"));

        for (int i = 0; i < filePath.length; i++) {
            images.add(createImageEntity("img" + i, filePath[i], tags));
            imagesThumbnails.add(createImageThumbnailEntity("img" + i, filePath[i]));
        }

        imageRepositoryImpl.saveAll(images);
        imageThumbnailRepositoryImpl.saveAll(imagesThumbnails);

//        Set<TagEntity> tagz = em.merge(tags);
//        System.out.println(em.getProperties());

        imageRepositoryImpl.save(new ImageEntity("img11", "123".getBytes(), "image/jpeg", 123456L, tags));

        imageRepositoryImpl.save(new ImageEntity("img12", "123".getBytes(), "image/jpeg", 123456L, tags));

        imageRepositoryImpl.save(new ImageEntity("img13", "123".getBytes(), "image/jpeg", 123456L, new HashSet<>(Arrays.asList(new TagEntity("Meow"), new TagEntity("valio")))));

//        Set<TagEntity> tagz = new HashSet<>(Arrays.asList(new TagEntity("Meow"), new TagEntity("valio")));

//        tagRepositoryImpl.save(new TagEntity("Popular"));
//        tagRepositoryImpl.save(new TagEntity("Meow"));

//        Set<TagEntity> tagz = new HashSet<>();
//        tagz.add(new TagEntity("Popular"));
//        tagz.add(new TagEntity("Meow"));
//        tagz.add(new TagEntity("Aha!"));





//        images.forEach(img -> System.out.println(img.getTitle()));
//        images.forEach(img -> imageThumbnailRepositoryImpl.save(new ImageThumbnailEntity(img.getTitle(),
//                MainHelper.createThumbnail(img.getData()), img.getMime(), img.getSize())));

//        bookService.save(new Book("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017"));
//        bookService.save(new Book("1002", "Apache Lucene Basics", "Rambabu Posa", "13-MAR-2017"));
//        bookService.save(new Book("1003", "Apache Solr Basics", "Rambabu Posa", "21-MAR-2017"));
//
//        //fuzzey search
//        Page<Book> books = bookService.findByAuthor("Rambabu", new PageRequest(0, 10));
//
//        //List<Book> books = bookService.findByTitle("Elasticsearch Basics");
//
//        books.forEach(x -> System.out.println(x));
    }

    public ImageEntity createImageEntity(String title, String filePath, Set<TagEntity> tags) throws IOException {
        File file = new File(filePath);

        // TODO: Get Image Data
        // init array with file length
        byte[] bytesArrayData = new byte[(int) file.length()];
        FileInputStream fis = new FileInputStream(filePath);
        // read file into bytes[]
        fis.read(bytesArrayData);
        fis.close();

        Path path = file.toPath();
        String mime = Files.probeContentType(path);
        Long size = file.length();

        return new ImageEntity(title, bytesArrayData, mime, size, tags);
    }

    public ImageThumbnailEntity createImageThumbnailEntity(String title, String filePath) throws IOException {
        File file = new File(filePath);
        Path path = file.toPath();
        String mime = Files.probeContentType(path);

        byte[] bytesArrayData = resize(file, mime);
        Long size = (long) bytesArrayData.length;

        return new ImageThumbnailEntity(title, bytesArrayData, mime, size);
    }

    public byte[] resize(File file, String mime) throws IOException {
        ByteArrayOutputStream thumbOutput = new ByteArrayOutputStream();
        BufferedImage thumbImg = null;
        BufferedImage img = ImageIO.read(file);

        // rotates image so the height would change according to width
//            BufferedImage rotatedImg = new BufferedImage(img.getHeight(), img.getWidth(), img.getType());
//            Graphics2D graphics = (Graphics2D) rotatedImg.getGraphics();
//            graphics.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), null);
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            ImageIO.write(rotatedImg, String.valueOf(img.getType()), bos);

        if (img.getWidth() < img.getHeight()) {
            // https://stackoverflow.com/questions/5837781/resize-image-to-fixed-size
            float ratio = 300F / img.getWidth();
            int width = Math.round(img.getHeight() * ratio);
            thumbImg = Scalr.resize(img, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, width, Scalr.OP_ANTIALIAS);
        } else {
            float ratio = 300F / img.getHeight();
            int width = Math.round(img.getWidth() * ratio);
            thumbImg = Scalr.resize(img, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, width, Scalr.OP_ANTIALIAS);
        }
        ImageIO.write(thumbImg, mime.split("/")[1], thumbOutput);

        return thumbOutput.toByteArray();
    }

    public byte[] getImageData(String filePath) throws IOException {
        File file = new File(filePath);

        // init array with file length
        byte[] bytesArray = new byte[(int) file.length()];

        FileInputStream fis = new FileInputStream(file);

        // read file into bytes[]
        fis.read(bytesArray);
        fis.close();

        return bytesArray;
    }

    public byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static Integer add(int x, int y) {
        return x + y;
    }

    //useful for debug
//    private void printElasticSearchInfo() {
//
//        System.out.println("--ElasticSearch-->");
//        Client client = es.getClient();
//        Map<String, String> asMap = client.settings().getAsMap();
//
//        asMap.forEach((k, v) -> {
//            System.out.println(k + " = " + v);
//        });
//        System.out.println("<--ElasticSearch--");
//    }
}
