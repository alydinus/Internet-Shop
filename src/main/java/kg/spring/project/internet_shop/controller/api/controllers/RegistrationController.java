package kg.spring.project.internet_shop.controller.api.controllers;

import kg.spring.project.internet_shop.controller.api.RegistrationApi;
import kg.spring.project.internet_shop.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController implements RegistrationApi {

  public ResponseEntity<String> registerUser(UserDTO userDTO) {
    return null;
  }
}
