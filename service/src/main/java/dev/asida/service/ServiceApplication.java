package dev.asida.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This application is created to get articles from existing DB.
 * For that purpose a MaridDB is used.
 * Application has a controller that with help of Spring annotation @RestController
 * converts returned Beans to JSON.
 *
 * @author Asida Yambay
 *
 */
@SpringBootApplication
public class ServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }
}
