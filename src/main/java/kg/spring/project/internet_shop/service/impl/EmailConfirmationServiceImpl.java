package kg.spring.project.internet_shop.service.impl;

import java.util.UUID;
import kg.spring.project.internet_shop.entity.EmailConfirmationToken;
import kg.spring.project.internet_shop.repository.EmailConfirmationRepository;
import kg.spring.project.internet_shop.service.EmailConfirmationService;
import kg.spring.project.internet_shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailConfirmationServiceImpl implements EmailConfirmationService {

  private final EmailConfirmationRepository emailConfirmationRepository;
  private final UserService userService;

  public String createEmailConfirmationToken(String email) {
    EmailConfirmationToken emailConfirmationToken = new EmailConfirmationToken();
    Long userId = userService.getUserByEmail(email).getId();
    String substring = UUID.randomUUID().toString().substring(0, 6);

    emailConfirmationToken.setToken(substring);
    emailConfirmationToken.setUserId(userId);

    emailConfirmationRepository.save(emailConfirmationToken);

    return substring;
  }


}
