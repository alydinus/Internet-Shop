package kg.spring.project.internet_shop.service;

import java.util.List;
import kg.spring.project.internet_shop.entity.User;
import kg.spring.project.internet_shop.enums.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

  User getUserById(Long id);

  User getUserByUsername(String username);

  User getUserByEmail(String email);

  List<User> getAllUsers();

  User createUser(String firstName, String lastName, String username, String email, String password,
      Role role);

  User updateUser(Long id, String firstName, String lastName, String username, String email, String password,
      Role role);

  User updateUserRefreshToken(Long id, String refreshToken);

  void deleteUser(Long id);
}
