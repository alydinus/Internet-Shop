package kg.spring.project.internet_shop.service;

import kg.spring.project.internet_shop.entity.User;

public interface VerificationTokenService {

  String createVerificationToken(String email);

  User getUserWithToken(String token);

}
