package kg.spring.project.internet_shop.dto.payload.request;

import lombok.Data;

@Data
public class JwtRequest {

  private String username;
  private String password;

}
