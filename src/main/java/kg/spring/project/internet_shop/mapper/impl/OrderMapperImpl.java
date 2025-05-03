package kg.spring.project.internet_shop.mapper.impl;

import java.util.ArrayList;
import java.util.List;
import kg.spring.project.internet_shop.dto.payload.response.OrderResponse;
import kg.spring.project.internet_shop.entity.Order;
import kg.spring.project.internet_shop.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapperImpl implements OrderMapper {

  private final OrderItemMapperImpl orderItemMapper;


  public OrderResponse toOrderResponse(Order order) {
    OrderResponse orderResponse = new OrderResponse();
    orderResponse.setOrderId(order.getId());
    orderResponse.setUserId(order.getUser().getId());
    orderResponse.setOrderItems(orderItemMapper.toOrderItemDTOList(order.getOrderItems()));
    orderResponse.setStatus(order.getStatus());
    orderResponse.setOrderDate(order.getOrderDate());
    return orderResponse;
  }

  public List<OrderResponse> toOrderResponseList(List<Order> orders) {
    List<OrderResponse> orderResponses = new ArrayList<>();
    for (Order order : orders) {
      OrderResponse orderResponse = toOrderResponse(order);
      orderResponses.add(orderResponse);
    }
    return orderResponses;
  }
}
