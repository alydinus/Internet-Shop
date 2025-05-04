package kg.spring.project.internet_shop.controller.api;

import kg.spring.project.internet_shop.dto.UserDTO;
import kg.spring.project.internet_shop.mapper.UserMapper;
import kg.spring.project.internet_shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final UserMapper userMapper;

  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> getCurrentUser(@PathVariable Long id) {
    return new ResponseEntity<>(userMapper.toUserDTO(userService.getUserById(id)), HttpStatus.OK);
  }
}
