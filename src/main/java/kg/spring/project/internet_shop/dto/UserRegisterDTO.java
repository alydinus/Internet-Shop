package kg.spring.project.internet_shop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class UserRegisterDTO {

  private String username;
  private String password;
  private String email;
  private String firstName;
  private String lastName;

}
