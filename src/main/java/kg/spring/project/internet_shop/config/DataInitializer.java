package kg.spring.project.internet_shop.config;

import kg.spring.project.internet_shop.entity.User;
import kg.spring.project.internet_shop.enums.Role;
import kg.spring.project.internet_shop.service.ProductService;
import kg.spring.project.internet_shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

  private final UserService userService;
  private final ProductService productService;

  public void run(ApplicationArguments args) throws Exception {
    User admin = userService.createUser("Alydin", "Alykulov", "alydinus",
        "alykulovalydin@gmail.com", "1", Role.ROLE_ADMIN);
    userService.updateUserActiveStatus(admin.getId(), true);

    User user  = userService.createUser("Alydin", "Alykulov", "alydinus1",
        "alykulovalydin123@gmail.com", "1", Role.ROLE_USER);
    userService.updateUserActiveStatus(user.getId(), true);

    productService.createProduct(
        "Iphone 14 Pro Max", 1000.0, "Iphone 14 Pro Max", 10, "PHONE");

  }
}
