package kg.spring.project.internet_shop.controller.api.controllers;

import kg.spring.project.internet_shop.controller.api.UserApi;
import kg.spring.project.internet_shop.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController implements UserApi {

  public ResponseEntity<UserDTO> getCurrentUser() {
    return null;
  }
}
