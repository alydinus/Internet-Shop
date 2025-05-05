package kg.spring.project.internet_shop.dto.payload.request;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Data;

@Data
public class CartItemUpdateRequest {

  @NotNull(message = "Cart ID cannot be null")
  private Long cartId;

  @NotNull(message = "Product ID cannot be null")
  private List<Long> productIds;

  @NotNull(message = "Quantity cannot be null")
  private List<Integer> quantity;

}
