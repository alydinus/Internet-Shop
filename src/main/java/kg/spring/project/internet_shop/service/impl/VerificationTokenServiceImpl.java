package kg.spring.project.internet_shop.service.impl;

import java.util.UUID;
import kg.spring.project.internet_shop.entity.User;
import kg.spring.project.internet_shop.entity.VerificationToken;
import kg.spring.project.internet_shop.repository.VerificationTokenRepository;
import kg.spring.project.internet_shop.service.UserService;
import kg.spring.project.internet_shop.service.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerificationTokenServiceImpl implements VerificationTokenService {

  private final VerificationTokenRepository verificationTokenRepository;
  private final UserService userService;

  public String createVerificationToken(String email) {
    String substring = UUID.randomUUID().toString().substring(0, 16);
    VerificationToken verificationToken = new VerificationToken();
    User user = userService.getUserByEmail(email);
    verificationToken.setToken(substring);
    verificationToken.setUser(user);
    verificationTokenRepository.save(verificationToken);
    return substring;
  }

  public User getUserWithToken(String token) {
    VerificationToken verificationToken = verificationTokenRepository.findByToken(token)
        .orElseThrow(() -> new RuntimeException("Verification token not found"));
    return verificationToken.getUser();
  }
}
