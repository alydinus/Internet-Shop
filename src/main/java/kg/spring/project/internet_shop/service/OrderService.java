package kg.spring.project.internet_shop.service;

import java.util.List;
import kg.spring.project.internet_shop.dto.OrderDTO;
import kg.spring.project.internet_shop.dto.OrderItemDTO;
import kg.spring.project.internet_shop.enums.OrderStatus;

public interface OrderService {

  OrderDTO createOrder(List<OrderItemDTO> items);

  OrderDTO getOrderById(Long id);

  List<OrderDTO> getAllOrders();

  void updateOrderStatus(Long id, OrderStatus status);

  void deleteOrder(Long id);
}
