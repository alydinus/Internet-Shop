package kg.spring.project.internet_shop.dto;

import java.time.LocalDateTime;
import java.util.List;
import kg.spring.project.internet_shop.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

  private Long id;
  private List<OrderItemDTO> items;
  private LocalDateTime orderDate;
  private OrderStatus status;

}
