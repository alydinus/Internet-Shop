package kg.spring.project.internet_shop.mapper;

import java.util.List;
import kg.spring.project.internet_shop.dto.OrderItemDTO;
import kg.spring.project.internet_shop.entity.OrderItem;

public interface OrderItemMapper {

  OrderItem toEntity(OrderItemDTO dto);
  List<OrderItemDTO> toOrderItemDTOList(List<OrderItem> orderItems);
  OrderItemDTO toOrderItemDTO(OrderItem orderItem);
}
