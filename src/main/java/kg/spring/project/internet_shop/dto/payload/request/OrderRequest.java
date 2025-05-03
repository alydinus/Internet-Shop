package kg.spring.project.internet_shop.dto.payload.request;

import java.util.List;
import kg.spring.project.internet_shop.dto.OrderItemDTO;
import lombok.Data;

@Data
public class OrderRequest {

  private Long userId;
  private List<OrderItemDTO> orderItems;

}
