package kg.spring.project.internet_shop.mapper;

import java.util.List;
import kg.spring.project.internet_shop.dto.payload.response.OrderResponse;
import kg.spring.project.internet_shop.entity.Order;

public interface OrderMapper {

  OrderResponse toOrderResponse(Order order);
  List<OrderResponse> toOrderResponseList(List<Order> orders);
}
