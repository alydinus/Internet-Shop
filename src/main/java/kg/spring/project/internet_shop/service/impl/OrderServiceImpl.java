package kg.spring.project.internet_shop.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import kg.spring.project.dto.OrderDTO;
import kg.spring.project.dto.OrderItemDTO;
import kg.spring.project.enums.OrderStatus;
import kg.spring.project.internet_shop.entity.Order;
import kg.spring.project.internet_shop.entity.OrderItem;
import kg.spring.project.internet_shop.entity.Product;
import kg.spring.project.internet_shop.entity.User;
import kg.spring.project.internet_shop.mapper.OrderMapper;
import kg.spring.project.internet_shop.repository.OrderRepository;
import kg.spring.project.internet_shop.repository.ProductRepository;
import kg.spring.project.internet_shop.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

  private final UserRepository userRepository;
  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;
  private final OrderMapper orderMapper;

  public OrderServiceImpl(UserRepository userRepository,
      OrderRepository orderRepository, ProductRepository productRepository, OrderMapper orderMapper) {
    this.userRepository = userRepository;
    this.orderRepository = orderRepository;
    this.productRepository = productRepository;
    this.orderMapper = orderMapper;
  }

  public OrderDTO createOrder(Long userId, List<OrderItemDTO> items) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));

    Order order = new Order();
    order.setUser(user);
    order.setOrderDate(LocalDateTime.now());
    order.setStatus(OrderStatus.NEW);

    List<OrderItem> orderItems = new ArrayList<>();
    for (OrderItemDTO dto : items) {
      Product product = productRepository.findById(dto.getProductId())
          .orElseThrow(() -> new RuntimeException("Product not found"));

      OrderItem orderItem = new OrderItem();
      orderItem.setOrder(order);
      orderItem.setProduct(product);
      orderItem.setQuantity(dto.getQuantity());
      orderItem.setPrice(product.getPrice());

      orderItems.add(orderItem);
    }
    order.setOrderItems(orderItems);
    orderRepository.save(order);

    return orderMapper.toOrderDTO(order);
  }

  public OrderDTO getOrderById(Long id) {
    Order order = orderRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Order not found"));
    return orderMapper.toOrderDTO(order);
  }

  public void updateOrderStatus(Long orderId, OrderStatus status) {
    Order order = orderRepository.findById(orderId)
        .orElseThrow(() -> new RuntimeException("Order not found"));
    order.setStatus(status);
    orderRepository.save(order);
  }

  public void deleteOrder(Long id) {
    orderRepository.deleteById(id);
  }
}
