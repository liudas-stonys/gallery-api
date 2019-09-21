package lt.liudas.services;

import lt.liudas.dao.*;
import lt.liudas.entities.*;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DbSeederServiceImpl implements DbSeederService, CommandLineRunner {
    @Autowired
    private ImageDao imageDaoImpl;
    @Autowired
    private ImageFullSizeDao imageFullSizeDaoImpl;
    @Autowired
    private UserDao userDaoImpl;
    @Autowired
    private UserService userServiceImpl;

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

        List<CategoryEntity> categories = new ArrayList<>();
        List<TagEntity> tags = new ArrayList<>();
        categories.add(new CategoryEntity("Animals"));
        categories.add(new CategoryEntity("people"));
        categories.add(new CategoryEntity("nature"));
        tags.add(new TagEntity("meow"));
        tags.add(new TagEntity("Aha!"));
        tags.add(new TagEntity("Popular"));

        for (int i = 0; i < filePath.length; i++) {
            ImageFullSize imageFullSize = imageFullSizeDaoImpl.save(createImageFullSizeEntity("img" + (i + 1), filePath[i]));
            imageDaoImpl.save(createImageEntity(imageFullSize.getId(), "img" + (i + 1), filePath[i], new HashSet<>(categories), new HashSet<>(tags)));
        }

        // TODO: Users
        userDaoImpl.save(userServiceImpl.createUserEntity("aeom", "aeom@aeom.ai", "meow", "admin"));
        userDaoImpl.save(userServiceImpl.createUserEntity("monika", "monika@aeom.ai", "meow", "user"));
    }

    public ImageFullSize createImageFullSizeEntity(String title, String filePath) throws IOException {
        File file = new File(filePath);
        byte[] bytesArrayData = new byte[(int) file.length()];
        FileInputStream fis = new FileInputStream(filePath);
        Long size = (long) fis.read(bytesArrayData);
        fis.close();

        Path path = file.toPath();
        String mime = Files.probeContentType(path);
        return new ImageFullSize(title, mime, bytesArrayData, size);
    }

    public ImageEntity createImageEntity(Long idImageFullSize, String title, String filePath, Set<CategoryEntity> categories, Set<TagEntity> tags) throws IOException {
        File file = new File(filePath);
        Path path = file.toPath();
        String mime = Files.probeContentType(path);

        byte[] bytesArrayData = resize(file, mime);
        Long size = (long) bytesArrayData.length;
        return new ImageEntity(idImageFullSize, title, mime, bytesArrayData, size, "Ajajai kokia graži nuotraukytė", categories, tags);
    }

    public byte[] resize(File file, String mime) throws IOException {
        ByteArrayOutputStream thumbOutput = new ByteArrayOutputStream();
        BufferedImage thumbImg = null;
        BufferedImage img = ImageIO.read(file);

        if (img.getWidth() < img.getHeight()) {
            float ratio = 300F / img.getWidth();
            int width = Math.round(img.getHeight() * ratio);
            thumbImg = Scalr.resize(img, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, width, Scalr.OP_ANTIALIAS);
        } else {
            float ratio = 300F / img.getHeight();
            int width = Math.round(img.getWidth() * ratio);
            thumbImg = Scalr.resize(img, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, width, Scalr.OP_ANTIALIAS);
        }
        ImageIO.write(thumbImg, mime.split("/")[1], thumbOutput);
        InputStream in = new ByteArrayInputStream(thumbOutput.toByteArray());
        BufferedImage thumb = ImageIO.read(in);
        return thumbOutput.toByteArray();
    }
}
