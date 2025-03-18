package kg.spring.project.internet_shop.service;

import kg.spring.project.internet_shop.entity.User;

public interface UserService {
  void save(User user);
  User findByUsername(String username);
  User findByEmail(String email);
  User findById(Long id);
}
