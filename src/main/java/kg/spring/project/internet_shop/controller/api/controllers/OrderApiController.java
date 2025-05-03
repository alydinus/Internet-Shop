package kg.spring.project.internet_shop.controller.api.controllers;

import java.util.List;
import kg.spring.project.internet_shop.controller.api.OrderApi;
import kg.spring.project.internet_shop.dto.payload.request.OrderRequest;
import kg.spring.project.internet_shop.dto.payload.response.OrderResponse;
import kg.spring.project.internet_shop.enums.OrderStatus;
import kg.spring.project.internet_shop.mapper.OrderMapper;
import kg.spring.project.internet_shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderApiController implements OrderApi {

  private final OrderService orderService;
  private final OrderMapper orderMapper;

  public ResponseEntity<OrderResponse> createOrder(OrderRequest orderRequest) {
    return new ResponseEntity<>(orderMapper.toOrderResponse(
        orderService.createOrder(orderRequest.getUserId(), orderRequest.getOrderItems())),
        HttpStatus.CREATED);
  }

  public ResponseEntity<OrderResponse> getOrderById(Long id) {
    return new ResponseEntity<>(orderMapper.toOrderResponse(orderService.getOrderById(id)),
        HttpStatus.OK);
  }

  public ResponseEntity<List<OrderResponse>> getAllOrdersByUserId(Long id) {
    return new ResponseEntity<>(orderMapper.toOrderResponseList(orderService.getAllOrdersByUserId(id)), HttpStatus.OK);
  }

  public ResponseEntity<List<OrderResponse>> getAllOrders() {
    return new ResponseEntity<>(orderMapper.toOrderResponseList(orderService.getAllOrders()), HttpStatus.OK);
  }

  public ResponseEntity<String> updateOrderStatus(Long id, OrderStatus status) {
    orderService.updateOrderStatus(id, status);
    return new ResponseEntity<>("Order updated successfully",HttpStatus.OK);
  }

  public ResponseEntity<String> deleteOrder(Long id) {
    orderService.deleteOrder(id);
    return new ResponseEntity<>("Order deleted successfully",HttpStatus.NO_CONTENT);
  }
}