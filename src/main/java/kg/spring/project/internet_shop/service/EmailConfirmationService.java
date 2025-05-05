package kg.spring.project.internet_shop.service;

public interface EmailConfirmationService {

  String createEmailConfirmationToken(String email);

  String getTokenByUserId(Long userId);

}
