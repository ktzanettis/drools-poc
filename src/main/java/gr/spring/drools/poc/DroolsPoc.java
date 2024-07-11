package gr.spring.drools.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication (scanBasePackages = {"gr.spring.drools.poc.**.**"})
@EnableMongoRepositories (basePackages = "gr.spring.drools.poc.repo")
public class DroolsPoc {

    public static void main(String[] args) {
        SpringApplication.run(DroolsPoc.class, args);
        System.out.println("Application is running...");
    }
}
