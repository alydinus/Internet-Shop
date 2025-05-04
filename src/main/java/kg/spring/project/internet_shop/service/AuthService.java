package kg.spring.project.internet_shop.service;

import kg.spring.project.internet_shop.dto.payload.response.JwtResponse;

public interface AuthService {

  JwtResponse login(String username, String password);

  void register(String username, String password, String confirmPassword, String email,
      String firstName, String lastName);

  JwtResponse refreshToken(String refreshToken);
}
