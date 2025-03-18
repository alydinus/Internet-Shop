package kg.spring.project.internet_shop.controller.api;

import java.util.List;
import kg.spring.project.internet_shop.dto.OrderDTO;
import kg.spring.project.internet_shop.dto.OrderItemDTO;
import kg.spring.project.internet_shop.enums.OrderStatus;
import kg.spring.project.internet_shop.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping("/{userId}")
  public ResponseEntity<OrderDTO> createOrder(@PathVariable Long userId, @RequestBody List<OrderItemDTO> items) {
    return ResponseEntity.ok(orderService.createOrder(userId, items));
  }

  @GetMapping("/{id}")
  public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
    return ResponseEntity.ok(orderService.getOrderById(id));
  }

  @PutMapping("/{id}/status")
  public ResponseEntity<Void> updateOrderStatus(@PathVariable Long id, @RequestParam OrderStatus status) {
    orderService.updateOrderStatus(id, status);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
    orderService.deleteOrder(id);
    return ResponseEntity.noContent().build();
  }
}