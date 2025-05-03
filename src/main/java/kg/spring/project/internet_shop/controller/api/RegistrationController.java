package kg.spring.project.internet_shop.controller.api;

import kg.spring.project.internet_shop.dto.UserRegisterDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController{

  public ResponseEntity<String> registerUser(UserRegisterDTO userDTO) {
    return null;
  }
}
