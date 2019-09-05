package lt.liudas;

import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

@SpringBootApplication
@EnableJpaAuditing
//@EnableJpaRepositories(basePackages = {"lt.liudas.repositoriesDAO"})
//@EntityScan(basePackages = {"lt.liudas"})
//@ComponentScan(basePackages = {"lt.liudas"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);

//        Test test = new Test();
//        test.printEntityManagerProperties();
    }
}