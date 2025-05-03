package kg.spring.project.internet_shop.dto;

import lombok.Data;

@Data
public class UserRegisterDTO {

  private String username;
  private String password;
  private String email;
  private String firstName;
  private String lastName;

}
