package lt.liudas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
//@EnableJpaRepositories(basePackages = {"lt.liudas"})
//@EntityScan(basePackages = {"lt.liudas"})
//@ComponentScan(basePackages = {"lt.liudas"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}