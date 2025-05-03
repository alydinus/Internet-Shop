package kg.spring.project.internet_shop.dto.payload.response;

import java.util.List;
import kg.spring.project.internet_shop.entity.CartItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {
  private Long id;
  private Long userId;
  private List<CartItem> items;
}
