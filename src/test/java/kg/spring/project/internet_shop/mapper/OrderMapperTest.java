package kg.spring.project.internet_shop.mapper;

import kg.spring.project.internet_shop.dto.OrderDTO;
import kg.spring.project.internet_shop.dto.OrderItemDTO;
import kg.spring.project.internet_shop.entity.Order;
import kg.spring.project.internet_shop.entity.OrderItem;
import kg.spring.project.internet_shop.enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderMapperTest {

  @Mock
  private OrderItemMapper orderItemMapper;

  @InjectMocks
  private OrderMapper orderMapper;

  private Order order;
  private OrderDTO orderDTO;
  private OrderItem orderItem;
  private OrderItemDTO orderItemDTO;

  @BeforeEach
  void setUp() {
    orderItem = new OrderItem();
    orderItem.setId(1L);
    orderItem.setQuantity(2);
    orderItem.setPrice(100.0);

    orderItemDTO = new OrderItemDTO();
    orderItemDTO.setProductId(1L);
    orderItemDTO.setQuantity(2);
    orderItemDTO.setPrice(100.0);

    order = new Order();
    order.setId(1L);
    order.setOrderDate(LocalDateTime.now());
    order.setStatus(OrderStatus.NEW);
    order.setOrderItems(Arrays.asList(orderItem));

    orderDTO = new OrderDTO();
    orderDTO.setId(1L);
    orderDTO.setOrderDate(order.getOrderDate());
    orderDTO.setStatus(OrderStatus.NEW);
    orderDTO.setItems(Arrays.asList(orderItemDTO));
  }

  @Test
  void toOrderDTO() {
    when(orderItemMapper.toOrderItemDTO(orderItem)).thenReturn(orderItemDTO);

    OrderDTO result = orderMapper.toOrderDTO(order);

    assertNotNull(result);
    assertEquals(order.getId(), result.getId());
    assertEquals(order.getOrderDate(), result.getOrderDate());
    assertEquals(order.getStatus(), result.getStatus());
    assertNotNull(result.getItems());
    assertEquals(1, result.getItems().size());
    assertEquals(orderItemDTO, result.getItems().get(0));

    verify(orderItemMapper, times(1)).toOrderItemDTO(orderItem);
  }


  @Test
  void toOrder() {
    Order result = orderMapper.toOrder(orderDTO);

    assertNotNull(result);
    assertEquals(orderDTO.getId(), result.getId());
    assertEquals(orderDTO.getOrderDate(), result.getOrderDate());
    assertEquals(orderDTO.getStatus(), result.getStatus());
    assertNull(result.getOrderItems());

    verify(orderItemMapper, never()).toOrderItemDTO(any());
  }


}
