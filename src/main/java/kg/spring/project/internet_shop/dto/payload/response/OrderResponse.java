package kg.spring.project.internet_shop.dto.payload.response;

import java.time.LocalDateTime;
import java.util.List;
import kg.spring.project.internet_shop.dto.OrderItemDTO;
import kg.spring.project.internet_shop.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

  private Long orderId;
  private Long userId;
  private List<OrderItemDTO> orderItems;
  private LocalDateTime orderDate;
  private OrderStatus status;

}
