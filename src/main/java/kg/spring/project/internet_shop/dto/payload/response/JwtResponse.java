package kg.spring.project.internet_shop.dto.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
  private String accessToken;
  private String refreshToken;
}
