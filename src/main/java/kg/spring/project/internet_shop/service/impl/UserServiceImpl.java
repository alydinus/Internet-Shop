package kg.spring.project.internet_shop.service.impl;

import java.util.List;
import kg.spring.project.internet_shop.dto.payload.request.UserRequest;
import kg.spring.project.internet_shop.entity.User;
import kg.spring.project.internet_shop.exception.exceptions.UserNotFoundException;
import kg.spring.project.internet_shop.repository.UserRepository;
import kg.spring.project.internet_shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  public User getUserById(Long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public void createUser(UserRequest user) {
    User newUser = new User();
    newUser.setFirstName(user.getFirstName());
    newUser.setLastName(user.getLastName());
    newUser.setUsername(user.getUsername());
    newUser.setEmail(user.getEmail());
    newUser.setPassword(user.getPassword());
    newUser.setRole(user.getRole());
    userRepository.save(newUser);
  }

  public void updateUser(Long id, UserRequest user) {
    User existingUser = userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    existingUser.setFirstName(user.getFirstName());
    existingUser.setLastName(user.getLastName());
    existingUser.setUsername(user.getUsername());
    existingUser.setEmail(user.getEmail());
    existingUser.setPassword(user.getPassword());
    existingUser.setRole(user.getRole());
    userRepository.save(existingUser);
  }

  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }
}
