package kg.spring.project.internet_shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import kg.spring.project.internet_shop.controller.api.controllers.OrderApiController;
import kg.spring.project.internet_shop.dto.OrderDTO;
import kg.spring.project.internet_shop.dto.OrderItemDTO;
import kg.spring.project.internet_shop.enums.OrderStatus;
import kg.spring.project.internet_shop.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OrderApiControllerTest {

  @Mock
  private OrderService orderService;

  @InjectMocks
  private OrderApiController orderApiController;

  private MockMvc mockMvc;
  private ObjectMapper objectMapper;

  private OrderDTO orderDTO;
  private OrderItemDTO orderItemDTO;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(orderApiController).build();
    objectMapper = new ObjectMapper();
    objectMapper.findAndRegisterModules();

    orderItemDTO = new OrderItemDTO();
    orderItemDTO.setProductId(1L);
    orderItemDTO.setQuantity(2);
    orderItemDTO.setPrice(100.0);

    orderDTO = new OrderDTO();
    orderDTO.setId(1L);
    orderDTO.setOrderDate(LocalDateTime.now());
    orderDTO.setStatus(OrderStatus.NEW);
    orderDTO.setItems(Arrays.asList(orderItemDTO));
  }

  @Test
  void createOrder() throws Exception {
    when(orderService.createOrder(any())).thenReturn(orderDTO);

    mockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(orderDTO)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(orderDTO.getId()))
        .andExpect(jsonPath("$.status").value(orderDTO.getStatus().toString()))
        .andExpect(jsonPath("$.items[0].productId").value(orderItemDTO.getProductId()))
        .andExpect(jsonPath("$.items[0].quantity").value(orderItemDTO.getQuantity()))
        .andExpect(jsonPath("$.items[0].price").value(orderItemDTO.getPrice()));

    verify(orderService, times(1)).createOrder(any());
  }

  @Test
  void getOrderById() throws Exception {
    when(orderService.getOrderById(1L)).thenReturn(orderDTO);

    mockMvc.perform(get("/api/orders/{id}", 1L))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(orderDTO.getId()))
        .andExpect(jsonPath("$.status").value(orderDTO.getStatus().toString()))
        .andExpect(jsonPath("$.items[0].productId").value(orderItemDTO.getProductId()))
        .andExpect(jsonPath("$.items[0].quantity").value(orderItemDTO.getQuantity()))
        .andExpect(jsonPath("$.items[0].price").value(orderItemDTO.getPrice()));

    verify(orderService, times(1)).getOrderById(1L);
  }

  @Test
  void getAllOrders() throws Exception {
    List<OrderDTO> orderDTOs = Arrays.asList(orderDTO);
    when(orderService.getAllOrders()).thenReturn(orderDTOs);

    mockMvc.perform(get("/api/orders"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].id").value(orderDTO.getId()))
        .andExpect(jsonPath("$[0].status").value(orderDTO.getStatus().toString()))
        .andExpect(jsonPath("$[0].items[0].productId").value(orderItemDTO.getProductId()))
        .andExpect(jsonPath("$[0].items[0].quantity").value(orderItemDTO.getQuantity()))
        .andExpect(jsonPath("$[0].items[0].price").value(orderItemDTO.getPrice()));

    verify(orderService, times(1)).getAllOrders();
  }

  @Test
  void updateOrderStatus() throws Exception {
    doNothing().when(orderService).updateOrderStatus(eq(1L), eq(OrderStatus.COMPLETED));

    mockMvc.perform(patch("/api/orders/{id}/status", 1L)
            .param("status", "COMPLETED"))
        .andExpect(status().isNoContent());

    verify(orderService, times(1)).updateOrderStatus(1L, OrderStatus.COMPLETED);
  }

  @Test
  void deleteOrder() throws Exception {
    doNothing().when(orderService).deleteOrder(1L);

    mockMvc.perform(delete("/api/orders/{id}", 1L))
        .andExpect(status().isNoContent());

    verify(orderService, times(1)).deleteOrder(1L);
  }
}