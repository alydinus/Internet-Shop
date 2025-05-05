package kg.spring.project.internet_shop.service.impl;

import io.jsonwebtoken.ExpiredJwtException;
import java.util.UUID;
import kg.spring.project.internet_shop.dto.payload.response.JwtResponse;
import kg.spring.project.internet_shop.entity.User;
import kg.spring.project.internet_shop.entity.VerificationToken;
import kg.spring.project.internet_shop.enums.Role;
import kg.spring.project.internet_shop.exception.exceptions.PasswordDoNotMatchException;
import kg.spring.project.internet_shop.exception.exceptions.UserAlreadyExistsException;
import kg.spring.project.internet_shop.exception.exceptions.UserIsNotActivated;
import kg.spring.project.internet_shop.exception.exceptions.WrongCredentials;
import kg.spring.project.internet_shop.service.AuthService;
import kg.spring.project.internet_shop.service.EmailConfirmationService;
import kg.spring.project.internet_shop.service.MailService;
import kg.spring.project.internet_shop.service.UserService;
import kg.spring.project.internet_shop.service.VerificationTokenService;
import kg.spring.project.internet_shop.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final AuthenticationManager authenticationManager;
  private final UserService userService;
  private final JwtTokenUtils jwtTokenUtils;
  private final MailService mailService;
  private final VerificationTokenService verificationTokenService;
  private final EmailConfirmationService emailConfirmationService;

  public JwtResponse login(String username, String password) {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(username, password));
    } catch (BadCredentialsException e) {
      throw new WrongCredentials("Invalid username or password");
    }

    UserDetails userDetails = userService.loadUserByUsername(username);
    String accessToken = jwtTokenUtils.generateAccessToken(userDetails);
    String refreshToken = jwtTokenUtils.generateRefreshToken(userDetails);
    User user = userService.getUserByUsername(username);
    if (!user.isActive()) {
      throw new UserIsNotActivated("Please confirm your email address");
    }
    userService.updateUserRefreshToken(user.getId(), refreshToken);


    String verificationToken = emailConfirmationService.createEmailConfirmationToken(user.getEmail());

    mailService.sendEmail(user.getEmail(),
        "Hello! " + user.getFirstName(),
        "Your verification token is: " + verificationToken);

    return new JwtResponse(accessToken, refreshToken);
  }

  public void register(String username, String password, String confirmPassword, String email,
      String firstName, String lastName) {
    if (!password.equals(confirmPassword)) {
      throw new PasswordDoNotMatchException("Passwords do not match");
    }

    if (userService.getUserByUsername(username) != null) {
      throw new UserAlreadyExistsException("User with this username already exists");
    }

    if (userService.getUserByEmail(email) != null) {
      throw new UserAlreadyExistsException("User with this email already exists");
    }
    userService.createUser(firstName, lastName, username, email, password, Role.ROLE_USER);
    String token = verificationTokenService.createVerificationToken(email);
    mailService.sendEmail(email,
        "Registration Confirmation",
        "Welcome to our service, " + firstName + "! Your registration is successful." +
            " Please confirm your email address by clicking the link below:\n" +
            "http://localhost:8080/api/auth/confirm?token=" + token);

  }

  public JwtResponse refreshToken(String refreshToken) {
    UserDetails userDetails = userService.loadUserByUsername(
        jwtTokenUtils.getUsername(refreshToken));
    if (refreshToken == null || !jwtTokenUtils.isTokenValid(refreshToken, userDetails)) {
      throw new ExpiredJwtException(null, null,
          "Refresh token is expired or invalid");
    }

    String newAccessToken = jwtTokenUtils.generateAccessToken(userDetails);
    String newRefreshToken = jwtTokenUtils.generateRefreshToken(userDetails);
    User user = userService.getUserByUsername(userDetails.getUsername());
    userService.updateUserRefreshToken(user.getId(), refreshToken);
    return new JwtResponse(newAccessToken, newRefreshToken);

  }

  public String confirmEmail(String token) {
    User user = verificationTokenService.getUserWithToken(token);
    if (user == null) {
      throw new UserAlreadyExistsException("User with this email does not exist");
    }
    user.setActive(true);
    userService.updateUserActiveStatus(user.getId(), true);
    return "Email confirmed successfully";
  }

  public String verifyEmail(String token) {

  }
}
