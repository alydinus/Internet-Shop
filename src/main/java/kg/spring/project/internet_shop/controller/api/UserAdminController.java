package kg.spring.project.internet_shop.controller.api;

import java.util.List;
import kg.spring.project.internet_shop.dto.UserDTO;
import kg.spring.project.internet_shop.dto.payload.request.UserRequest;
import kg.spring.project.internet_shop.mapper.UserMapper;
import kg.spring.project.internet_shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class UserAdminController {

  private final UserService userService;
  private final UserMapper userMapper;

  @GetMapping
  public ResponseEntity<List<UserDTO>> getAllUsers() {
    return new ResponseEntity<>(userMapper.toUserDTOList(userService.getAllUsers()), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
    return new ResponseEntity<>(userMapper.toUserDTO(userService.getUserById(id)), HttpStatus.OK);
  }

  @PostMapping("/create")
  public ResponseEntity<UserDTO> createUser(@RequestBody UserRequest userRequest) {
    System.out.println("userRequest = " + userRequest);
    return new ResponseEntity<>(userMapper.toUserDTO(userService.createUser(userRequest)),
        HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserRequest userDTO) {
    return new ResponseEntity<>(
        userMapper.toUserDTO(userService.updateUser(id, userDTO)), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
  }
}
