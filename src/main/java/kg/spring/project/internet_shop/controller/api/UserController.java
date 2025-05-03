package kg.spring.project.internet_shop.controller.api;

import kg.spring.project.internet_shop.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController{

  public ResponseEntity<UserDTO> getCurrentUser() {
    return null;
  }
}
