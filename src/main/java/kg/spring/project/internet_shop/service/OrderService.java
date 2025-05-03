package kg.spring.project.internet_shop.service;

import java.util.List;
import kg.spring.project.internet_shop.dto.OrderItemDTO;
import kg.spring.project.internet_shop.entity.Order;
import kg.spring.project.internet_shop.enums.OrderStatus;

public interface OrderService {

  Order createOrder(Long userId, List<OrderItemDTO> orderItems);

  Order getOrderById(Long id);

  List<Order> getAllOrders();

  List<Order> getAllOrdersByUserId(Long id);

  void updateOrderStatus(Long id, OrderStatus status);

  void deleteOrder(Long id);
}
