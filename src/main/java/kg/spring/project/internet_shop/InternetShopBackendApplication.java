package kg.spring.project.internet_shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"kg.spring.project.mapper", "kg.spring.project.internet_shop"})

public class InternetShopBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(InternetShopBackendApplication.class, args);
  }

}
