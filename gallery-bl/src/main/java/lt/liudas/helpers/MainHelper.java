package lt.liudas.helpers;

import org.imgscalr.Scalr;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MainHelper {

    public static ByteArrayOutputStream createThumbnailFromMultipartFile(MultipartFile orginalFile, Integer width) throws IOException {
        ByteArrayOutputStream thumbOutput = new ByteArrayOutputStream();
        BufferedImage thumbImg = null;
        BufferedImage img = ImageIO.read(orginalFile.getInputStream());
        thumbImg = Scalr.resize(img, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, width, Scalr.OP_ANTIALIAS);
        ImageIO.write(thumbImg, orginalFile.getContentType().split("/")[1], thumbOutput);
        return thumbOutput;
    }

    public static ByteArrayOutputStream createThumbnailFromByteArray(byte[] data, String mime, Integer width) throws IOException {
        ByteArrayOutputStream thumbOutput = new ByteArrayOutputStream();
        BufferedImage thumbImg = null;
//        BufferedImage img = ImageIO.read(orginalFile.getInputStream());
        BufferedImage img = createImageFromBytes(data);
        thumbImg = Scalr.resize(img, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, width, Scalr.OP_ANTIALIAS);
        ImageIO.write(thumbImg, mime, thumbOutput);
        return thumbOutput;
    }

    public static BufferedImage createImageFromBytes(byte[] imageData) {
        ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
        try {
            return ImageIO.read(bais);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
