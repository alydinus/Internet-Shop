package kg.spring.project.internet_shop.service.impl;

import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;
import kg.spring.project.internet_shop.entity.User;
import kg.spring.project.internet_shop.enums.Role;
import kg.spring.project.internet_shop.exception.exceptions.UserNotFoundException;
import kg.spring.project.internet_shop.repository.UserRepository;
import kg.spring.project.internet_shop.security.JwtFilter;
import kg.spring.project.internet_shop.service.CartService;
import kg.spring.project.internet_shop.service.UserService;
import kg.spring.project.internet_shop.util.JwtTokenUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final CartService cartService;
  private final JwtFilter jwtFilter;
  private final JwtTokenUtils jwtTokenUtils;

  public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
      @Lazy CartService cartService, JwtFilter jwtFilter) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.cartService = cartService;
    this.jwtFilter = jwtFilter;
    this.jwtTokenUtils = jwtFilter.getJwtTokenUtils();
  }

  public User getUserById(Long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User getUserByUsername(String username) {
    if (userRepository.findByUsername(username).isPresent()) {
      return userRepository.findByUsername(username).get();
    } else {
      return null;
    }
  }

  public User getUserByEmail(String email) {
    if (userRepository.findByEmail(email).isPresent()) {
      return userRepository.findByEmail(email).get();
    } else {
      return null;
    }
  }

  public User getCurrentUser() {
    String token = jwtFilter.getJwt();
    String username = jwtTokenUtils.getUsername(token);
    return getUserByUsername(username);
  }

  public User createUser(String firstName, String lastName, String username, String email,
      String password,
      Role role) {
    User newUser = new User();
    newUser.setFirstName(firstName);
    newUser.setLastName(lastName);
    newUser.setUsername(username);
    newUser.setEmail(email);
    newUser.setPassword(passwordEncoder.encode(password));
    newUser.setRole(role);
    newUser.setRegistrationDate(LocalDateTime.now());

    userRepository.save(newUser);

    cartService.createCart(newUser.getId());

    return newUser;
  }

  public User updateUser(Long id, String firstName, String lastName, String username, String email,
      String password, Role role) {
    User existingUser = userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    existingUser.setFirstName(firstName);
    existingUser.setLastName(lastName);
    existingUser.setUsername(username);
    existingUser.setEmail(email);
    existingUser.setPassword(password);
    existingUser.setRole(role);
    userRepository.save(existingUser);
    return existingUser;
  }

  public User updateUserActiveStatus(Long id, boolean isActive) {
    User user = getUserById(id);
    if (user == null) {
      throw new UserNotFoundException("User not found with id: " + id);
    }

    user.setActive(isActive);
    return userRepository.save(user);
  }

  public User updateUserRefreshToken(Long id, String refreshToken) {
    User existingUser = userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    existingUser.setRefreshToken(refreshToken);
    userRepository.save(existingUser);
    return existingUser;
  }

  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }

  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username)
        .orElseThrow(
            () -> new UsernameNotFoundException("User not found with username: " + username));
    return new org.springframework.security.core.userdetails.User(
        user.getUsername(),
        user.getPassword(),
        Stream.of(user.getRole()).map(role -> new SimpleGrantedAuthority(role.toString())).toList()
    );
  }


}
