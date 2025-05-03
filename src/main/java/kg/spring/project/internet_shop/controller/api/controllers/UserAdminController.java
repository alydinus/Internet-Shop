package kg.spring.project.internet_shop.controller.api.controllers;

import java.util.List;
import kg.spring.project.internet_shop.controller.api.UserAdminApi;
import kg.spring.project.internet_shop.dto.UserDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/users")
public class UserAdminController implements UserAdminApi {

  public List<UserDTO> getAllUsers() {
    return List.of();
  }

  public UserDTO getUserById(Long id) {
    return null;
  }

  public UserDTO createUser(UserDTO userDTO) {
    return null;
  }

  public UserDTO updateUser(Long id, UserDTO userDTO) {
    return null;
  }

  public void deleteUser(Long id) {

  }
}
