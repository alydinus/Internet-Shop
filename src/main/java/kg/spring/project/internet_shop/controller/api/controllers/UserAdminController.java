package kg.spring.project.internet_shop.controller.api.controllers;

import java.util.List;
import kg.spring.project.internet_shop.controller.api.UserAdminApi;
import kg.spring.project.internet_shop.dto.UserDTO;
import kg.spring.project.internet_shop.dto.payload.request.UserRequest;
import kg.spring.project.internet_shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class UserAdminController implements UserAdminApi {

  private final UserService userService;

  public List<UserDTO> getAllUsers() {
    return List.of();
  }

  public UserDTO getUserById(Long id) {
    return null;
  }

  public UserDTO createUser(UserRequest userRequest) {
    return null;
  }

  public UserDTO updateUser(Long id, UserDTO userDTO) {
    return null;
  }

  public void deleteUser(Long id) {

  }
}
