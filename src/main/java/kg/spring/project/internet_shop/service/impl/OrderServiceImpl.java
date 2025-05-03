package kg.spring.project.internet_shop.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import kg.spring.project.internet_shop.dto.OrderItemDTO;
import kg.spring.project.internet_shop.entity.Order;
import kg.spring.project.internet_shop.entity.OrderItem;
import kg.spring.project.internet_shop.entity.User;
import kg.spring.project.internet_shop.enums.OrderStatus;
import kg.spring.project.internet_shop.exception.exceptions.OrderNotFoundException;
import kg.spring.project.internet_shop.exception.exceptions.UserNotFoundException;
import kg.spring.project.internet_shop.mapper.OrderItemMapper;
import kg.spring.project.internet_shop.repository.OrderRepository;
import kg.spring.project.internet_shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final UserServiceImpl userServiceImpl;
  private final OrderItemMapper orderItemMapper;

  public Order createOrder(Long userId, List<OrderItemDTO> orderItems) {
    Order order = new Order();
    try {
      List<OrderItem> orderItemList = orderItems.stream()
          .map(orderItemMapper::toEntity)
          .toList();
      User user = userServiceImpl.getUserById(userId);
      order.setUser(user);
      order.setOrderItems(orderItemList);
      order.setStatus(OrderStatus.NEW);
      order.setOrderDate(LocalDateTime.now());
      return orderRepository.save(order);
    } catch (UserNotFoundException | NullPointerException e) {
      throw new UserNotFoundException("User not found with id: " + userId);
    } catch (Exception e) {
      throw new RuntimeException("Error creating order: " + e.getMessage());
    }
  }

  public Order getOrderById(Long id) {
    return orderRepository.findById(id)
        .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + id));
  }

  public List<Order> getAllOrders() {
    return orderRepository.findAll();
  }

  public List<Order> getAllOrdersByUserId(Long id) {
    List<Order> orders = orderRepository.findAllByUserId(id);
    if (orders.isEmpty()) {
      throw new OrderNotFoundException("No orders found for user with id: " + id);
    }

    return orders;
  }

  public void updateOrderStatus(Long orderId, OrderStatus status) {
    Order order = getOrderById(orderId);
    order.setStatus(status);
    orderRepository.save(order);
  }

  public void deleteOrder(Long id) {
    if (orderRepository.existsById(id)) {
      orderRepository.deleteById(id);
    } else {
      throw new OrderNotFoundException("Order not found with id: " + id);
    }
  }
}
