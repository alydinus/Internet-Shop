package kg.spring.project.internet_shop.controller.api;

import kg.spring.project.internet_shop.dto.payload.request.JwtRequest;
import kg.spring.project.internet_shop.dto.payload.request.RefreshTokenRequest;
import kg.spring.project.internet_shop.dto.payload.request.RegistrationRequest;
import kg.spring.project.internet_shop.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody JwtRequest jwtRequest) {
    return new ResponseEntity<>(
        authService.login(jwtRequest.getUsername(), jwtRequest.getPassword()),
        HttpStatus.OK);
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody RegistrationRequest request) {
    authService.register(request.getUsername(), request.getPassword(),
        request.getConfirmPassword(), request.getEmail(), request.getFirstName(),
        request.getLastName());
    return new ResponseEntity<>("You have registered successfully", HttpStatus.OK);
  }

  @PostMapping("/refresh")
  public ResponseEntity<?> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
    return new ResponseEntity<>(authService.refreshToken(refreshTokenRequest.getRefreshToken()),
        HttpStatus.OK);
  }

  @GetMapping("/confirm")
  public ResponseEntity<?> confirmEmailRegistatraion(@RequestParam String token) {
    return new ResponseEntity<>(authService.confirmEmail(token), HttpStatus.OK);
  }
  
  @PostMapping("/2fa")
  public ResponseEntity<?> confirmEmail(@RequestParam String email, @RequestParam String token) {
    return new ResponseEntity<>(authService.verifyEmail(email, token), HttpStatus.OK);
  }
}
