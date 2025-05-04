package kg.spring.project.internet_shop.config;

import kg.spring.project.internet_shop.enums.Role;
import kg.spring.project.internet_shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

  private final UserService userService;

  public void run(ApplicationArguments args) throws Exception {
    userService.createUser("Alydin", "Alykulov", "alydinus",
        "alykulovalydin@gmail.com", "1", Role.ROLE_ADMIN);
  }
}
