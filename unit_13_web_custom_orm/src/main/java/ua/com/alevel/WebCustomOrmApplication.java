package ua.com.alevel;

import org.dinix.orm.DiNixOrmApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebCustomOrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebCustomOrmApplication.class, args);
        DiNixOrmApplication.run(WebCustomOrmApplication.class);
    }
}
