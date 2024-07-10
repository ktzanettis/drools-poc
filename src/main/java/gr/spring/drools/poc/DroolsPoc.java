package gr.spring.drools.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (scanBasePackages = {"gr.spring.drools.poc.**.**"})
public class DroolsPoc {

    public static void main(String[] args) {
        SpringApplication.run(DroolsPoc.class, args);
        System.out.println("Application is running...");
    }
}
