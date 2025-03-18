package kg.spring.project.internet_shop.mapper;

import java.util.List;
import java.util.stream.Collectors;
import kg.spring.project.internet_shop.dto.OrderDTO;
import kg.spring.project.internet_shop.dto.OrderItemDTO;
import kg.spring.project.internet_shop.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

  private final OrderItemMapper orderItemMapper;

  public OrderMapper(OrderItemMapper orderItemMapper) {
    this.orderItemMapper = orderItemMapper;
  }

  public OrderDTO toOrderDTO(Order order) {
    if (order == null) return null;

    OrderDTO dto = new OrderDTO();
    dto.setId(order.getId());
    dto.setUserId(order.getUser().getId());
    dto.setOrderDate(order.getOrderDate());
    dto.setStatus(order.getStatus());

    List<OrderItemDTO> items = order.getOrderItems().stream()
        .map(orderItemMapper::toOrderItemDTO)
        .collect(Collectors.toList());
    dto.setItems(items);

    return dto;
  }

  public Order toOrder(OrderDTO dto) {
    if (dto == null) return null;

    Order order = new Order();
    order.setId(dto.getId());
    order.setOrderDate(dto.getOrderDate());
    order.setStatus(dto.getStatus());

    return order;
  }


}
