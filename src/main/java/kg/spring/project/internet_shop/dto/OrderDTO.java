package kg.spring.project.internet_shop.dto;

import java.time.LocalDateTime;
import java.util.List;
import kg.spring.project.internet_shop.enums.OrderStatus;

public class OrderDTO {

  private Long id;
  private List<OrderItemDTO> items;
  private LocalDateTime orderDate;
  private OrderStatus status;

  public OrderDTO() {
  }

  public OrderDTO(Long id, List<OrderItemDTO> items, LocalDateTime orderDate,
      OrderStatus status) {
    this.id = id;
    this.items = items;
    this.orderDate = orderDate;
    this.status = status;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<OrderItemDTO> getItems() {
    return items;
  }

  public void setItems(List<OrderItemDTO> items) {
    this.items = items;
  }

  public LocalDateTime getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(LocalDateTime orderDate) {
    this.orderDate = orderDate;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }
}
