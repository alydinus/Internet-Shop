package kg.spring.project.internet_shop.dto.payload.request;

import lombok.Data;

@Data
public class RegistrationRequest {
  private String username;
  private String password;
  private String confirmPassword;
  private String email;
  private String firstName;
  private String lastName;

}
