package by.ruslan.radzevich.carsharingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "by.ruslan.radzevich")
@EnableJpaRepositories(basePackages = "by.ruslan.radzevich.repository")
@EntityScan(basePackages = "by.ruslan.radzevich.model.entity")
public class CarsharingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarsharingServiceApplication.class, args);
    }

}

