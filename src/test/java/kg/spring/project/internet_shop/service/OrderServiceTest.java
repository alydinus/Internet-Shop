package kg.spring.project.internet_shop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import kg.spring.project.internet_shop.dto.payload.response.OrderResponse;
import kg.spring.project.internet_shop.dto.OrderItemDTO;
import kg.spring.project.internet_shop.entity.Order;
import kg.spring.project.internet_shop.entity.OrderItem;
import kg.spring.project.internet_shop.entity.Product;
import kg.spring.project.internet_shop.enums.OrderStatus;
import kg.spring.project.internet_shop.mapper.OrderMapper;
import kg.spring.project.internet_shop.repository.OrderRepository;
import kg.spring.project.internet_shop.repository.ProductRepository;
import kg.spring.project.internet_shop.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

  @Mock
  private OrderRepository orderRepository;

  @Mock
  private ProductRepository productRepository;

  @Mock
  private OrderMapper orderMapper;

  @InjectMocks
  private OrderServiceImpl orderService;

  private Order order;
  private OrderResponse orderResponse;
  private Product product;
  private OrderItemDTO orderItemDTO;
  private List<OrderItem> orderItems;

  @BeforeEach
  void setUp() {
    product = new Product();
    product.setId(1L);
    product.setPrice(100.0);

    order = new Order();
    order.setId(1L);
    order.setOrderDate(LocalDateTime.now());
    order.setStatus(OrderStatus.NEW);

    orderItemDTO = new OrderItemDTO();
    orderItemDTO.setProductId(1L);
    orderItemDTO.setQuantity(2);

    OrderItem orderItem = new OrderItem();
    orderItem.setOrder(order);
    orderItem.setProduct(product);
    orderItem.setQuantity(orderItemDTO.getQuantity());
    orderItem.setPrice(product.getPrice());

    orderItems = Arrays.asList(orderItem);
    order.setOrderItems(orderItems);

    orderResponse = new OrderResponse();
    orderResponse.setId(1L);
    orderResponse.setOrderDate(order.getOrderDate());
    orderResponse.setStatus(OrderStatus.NEW);
  }

  @Test
  void createOrder() {
    List<OrderItemDTO> items = Arrays.asList(orderItemDTO);
    when(productRepository.findById(1L)).thenReturn(Optional.of(product));
    when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> {
      Order savedOrder = invocation.getArgument(0);
      savedOrder.setId(1L);
      return savedOrder;
    });
    when(orderMapper.toOrderDTO(any(Order.class))).thenReturn(orderResponse);

    OrderResponse result = orderService.createOrder(items);

    assertNotNull(result);
    assertEquals(orderResponse, result);
    verify(productRepository, times(1)).findById(1L);
    verify(orderRepository, times(1)).save(any(Order.class));
    verify(orderMapper, times(1)).toOrderDTO(any(Order.class));
  }

  @Test
  void getOrderById() {
    when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
    when(orderMapper.toOrderDTO(order)).thenReturn(orderDTO);

    OrderDTO result = orderService.getOrderById(1L);

    assertNotNull(result);
    assertEquals(orderDTO, result);
    verify(orderRepository, times(1)).findById(1L);
    verify(orderMapper, times(1)).toOrderDTO(order);
  }

  @Test
  void updateOrderStatus() {
    when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
    when(orderRepository.save(order)).thenReturn(order);

    orderService.updateOrderStatus(1L, OrderStatus.COMPLETED);

    assertEquals(OrderStatus.COMPLETED, order.getStatus());
    verify(orderRepository, times(1)).findById(1L);
    verify(orderRepository, times(1)).save(order);
  }

  @Test
  void deleteOrder() {
    orderService.deleteOrder(1L);

    verify(orderRepository, times(1)).deleteById(1L);
  }

  @Test
  void getAllOrders() {
    List<Order> orders = Arrays.asList(order);
    when(orderRepository.findAll()).thenReturn(orders);
    when(orderMapper.toOrderDTO(order)).thenReturn(orderDTO);

    List<OrderDTO> result = orderService.getAllOrders();

    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals(orderDTO, result.get(0));
    verify(orderRepository, times(1)).findAll();
    verify(orderMapper, times(1)).toOrderDTO(order);
  }

}