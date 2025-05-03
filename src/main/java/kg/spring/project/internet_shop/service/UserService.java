package kg.spring.project.internet_shop.service;

import java.util.List;
import kg.spring.project.internet_shop.dto.payload.request.UserRequest;
import kg.spring.project.internet_shop.entity.User;

public interface UserService {
  User getUserById(Long id);

  List<User> getAllUsers();

  User createUser(UserRequest user);

  User updateUser(Long id, UserRequest user);

  void deleteUser(Long id);
}
