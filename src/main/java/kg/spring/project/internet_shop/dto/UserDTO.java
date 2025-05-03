package kg.spring.project.internet_shop.dto;

import kg.spring.project.internet_shop.enums.Role;
import lombok.Data;

@Data
public class UserDTO {

  private Long id;
  private String firstName;
  private String lastName;
  private String username;
  private String email;
  private String password;
  private Role role;
}
