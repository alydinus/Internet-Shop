package kg.spring.project.internet_shop.dto.payload.response;

import java.util.List;
import kg.spring.project.internet_shop.dto.CartItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {
  private Long id;
  private Long userId;
  private List<CartItemDTO> items;
}
