package lt.liudas.services;

import lt.liudas.entities.ImageEntity;
import lt.liudas.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DbSeederService implements CommandLineRunner {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public void run(String... args) throws Exception {
//        printElasticSearchInfo();

        ImageEntity img1 = new ImageEntity("img1", null);
        ImageEntity img2 = new ImageEntity("img2", null);
        ImageEntity img3 = new ImageEntity("img3", null);

        List<ImageEntity> images = new ArrayList<>();
        images.add(img1);
        images.add(img2);
        images.add(img3);

        images.forEach(img -> System.out.println(img.getTitle()));

        this.imageRepository.saveAll(images);

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

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i+1), 16));
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
