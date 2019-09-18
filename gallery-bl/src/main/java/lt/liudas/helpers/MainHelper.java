package lt.liudas.helpers;

import org.imgscalr.Scalr;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

public class MainHelper {

    public static byte[] createThumbnailFromMultipartFile(MultipartFile orginalFile, Integer width) throws IOException {
        ByteArrayOutputStream thumbOutput = new ByteArrayOutputStream();
        BufferedImage thumbImg = null;
        BufferedImage img = ImageIO.read(orginalFile.getInputStream());
        if (img.getWidth() < img.getHeight()) {
            // https://stackoverflow.com/questions/5837781/resize-image-to-fixed-size
            float ratio = (float) width / img.getWidth();
            int newWidth = Math.round(img.getHeight() * ratio);
            thumbImg = Scalr.resize(img, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, newWidth, Scalr.OP_ANTIALIAS);
        } else {
            float ratio = (float) width / img.getHeight();
            int newWidth = Math.round(img.getWidth() * ratio);
            thumbImg = Scalr.resize(img, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, newWidth, Scalr.OP_ANTIALIAS);
        }
        ImageIO.write(thumbImg, orginalFile.getContentType().split("/")[1], thumbOutput);
        InputStream in = new ByteArrayInputStream(thumbOutput.toByteArray());
        BufferedImage thumb = ImageIO.read(in);
        return thumbOutput.toByteArray();
    }

    public static <T> void printList(List<T> data) {
//        System.out.print("[");
//        data.forEach(item -> System.out.print("\"" + item + "\", "));
//        System.out.println("]");
        System.out.println("[" + data.stream().map(item -> "\"" + item + "\"").collect(Collectors.joining(", ")) + "]");
    }

//    // TODO: Log ResultSet as JSON
//    // https://stackoverflow.com/questions/18960446/how-to-convert-a-java-resultset-into-json
//    public static <T> void logArrayListAsJson(List<T> data) {
//        Iterator<T> itr = null;
//        itr = data.listIterator();
//        while (itr.hasNext()) {
//            int totalRows = data.getMetaData().getColumnCount();
//            System.out.println();
//            for (int i = 0; i < totalRows; i++) {
//                JSONObject jsonObject = new JSONObject();
//                jsonObject.put(resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase(), resultSet.getObject(i + 1));
//                System.out.println(jsonObject);
//            }
//        }
//    }
}
