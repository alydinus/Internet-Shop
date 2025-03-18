package kg.spring.project.internet_shop.service;

import java.util.List;
import kg.spring.project.dto.OrderDTO;
import kg.spring.project.dto.OrderItemDTO;
import kg.spring.project.enums.OrderStatus;

public interface OrderService {

  OrderDTO createOrder(Long userId, List<OrderItemDTO> items);

  OrderDTO getOrderById(Long id);

  void updateOrderStatus(Long id, OrderStatus status);

  void deleteOrder(Long id);
}
