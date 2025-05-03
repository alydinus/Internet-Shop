package kg.spring.project.internet_shop.dto.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CartItemRequest {

  @NotNull(message = "Cart ID cannot be null")
  private Long cartId;

  @NotNull(message = "Product ID cannot be null")
  private Long productId;

  private int quantity;

}
