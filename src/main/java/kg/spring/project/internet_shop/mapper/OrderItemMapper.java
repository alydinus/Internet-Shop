package kg.spring.project.internet_shop.mapper;

import kg.spring.project.internet_shop.dto.OrderItemDTO;
import kg.spring.project.internet_shop.entity.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

  public OrderItemDTO toOrderItemDTO(OrderItem item) {
    OrderItemDTO dto = new OrderItemDTO();
    dto.setProductId(item.getProduct().getId());
    dto.setQuantity(item.getQuantity());
    dto.setPrice(item.getPrice());
    return dto;
  }

  public OrderItem toOrderItem(OrderItemDTO dto) {
    OrderItem item = new OrderItem();
    item.setQuantity(dto.getQuantity());
    item.setPrice(dto.getPrice());
    return item;
  }

}
