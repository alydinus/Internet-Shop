package kg.spring.project.internet_shop.controller.api.controllers;

import java.util.List;
import kg.spring.project.internet_shop.controller.api.OrderApi;
import kg.spring.project.internet_shop.dto.OrderDTO;
import kg.spring.project.internet_shop.enums.OrderStatus;
import kg.spring.project.internet_shop.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderApiController implements OrderApi {

  private final OrderService orderService;

  public OrderApiController(OrderService orderService) {
    this.orderService = orderService;
  }

  public ResponseEntity<OrderDTO> createOrder(OrderDTO orderDTO) {
    return ResponseEntity.ok(orderService.createOrder(orderDTO.getItems()));
  }

  public ResponseEntity<OrderDTO> getOrderById(Long id) {
    return ResponseEntity.ok(orderService.getOrderById(id));
  }

  public ResponseEntity<List<OrderDTO>> getAllOrders() {
    return ResponseEntity.ok(orderService.getAllOrders());
  }

  public ResponseEntity<Void> updateOrderStatus(Long id, OrderStatus status) {
    orderService.updateOrderStatus(id, status);
    return ResponseEntity.noContent().build();
  }

  public ResponseEntity<Void> deleteOrder(Long id) {
    orderService.deleteOrder(id);
    return ResponseEntity.noContent().build();
  }
}