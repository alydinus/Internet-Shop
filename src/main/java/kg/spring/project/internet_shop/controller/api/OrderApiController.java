package kg.spring.project.internet_shop.controller.api;

import java.util.List;
import kg.spring.project.internet_shop.dto.payload.request.OrderRequest;
import kg.spring.project.internet_shop.dto.payload.response.OrderResponse;
import kg.spring.project.internet_shop.enums.OrderStatus;
import kg.spring.project.internet_shop.mapper.OrderMapper;
import kg.spring.project.internet_shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderApiController {

  private final OrderService orderService;
  private final OrderMapper orderMapper;

  @PostMapping
  public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
    return new ResponseEntity<>(orderMapper.toOrderResponse(
        orderService.createOrder(orderRequest.getUserId(), orderRequest.getOrderItems())),
        HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
    return new ResponseEntity<>(orderMapper.toOrderResponse(orderService.getOrderById(id)),
        HttpStatus.OK);
  }

  @GetMapping("/user/{id}")
  public ResponseEntity<List<OrderResponse>> getAllOrdersByUserId(@PathVariable Long id) {
    return new ResponseEntity<>(orderMapper.toOrderResponseList(orderService.getAllOrdersByUserId(id)), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<OrderResponse>> getAllOrders() {
    return new ResponseEntity<>(orderMapper.toOrderResponseList(orderService.getAllOrders()), HttpStatus.OK);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<String> updateOrderStatus(@PathVariable Long id, @RequestParam OrderStatus status) {
    orderService.updateOrderStatus(id, status);
    return new ResponseEntity<>("Order updated successfully",HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
    orderService.deleteOrder(id);
    return new ResponseEntity<>("Order deleted successfully",HttpStatus.NO_CONTENT);
  }
}