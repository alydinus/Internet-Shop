package kg.spring.project.internet_shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kg.spring.project.internet_shop.controller.api.controllers.OrderApiController;
import kg.spring.project.internet_shop.dto.payload.response.OrderResponse;
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

  private OrderResponse orderResponse;
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

    orderResponse = new OrderResponse();
    orderResponse.setId(1L);
    orderResponse.setOrderDate(LocalDateTime.now());
    orderResponse.setStatus(OrderStatus.NEW);
    orderResponse.setItems(Arrays.asList(orderItemDTO));
  }

  @Test
  void createOrder() throws Exception {
    when(orderService.createOrder(any())).thenReturn(orderResponse);

    mockMvc.perform(post("/api/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(orderResponse)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(orderResponse.getId()))
        .andExpect(jsonPath("$.status").value(orderResponse.getStatus().toString()))
        .andExpect(jsonPath("$.items[0].productId").value(orderItemDTO.getProductId()))
        .andExpect(jsonPath("$.items[0].quantity").value(orderItemDTO.getQuantity()))
        .andExpect(jsonPath("$.items[0].price").value(orderItemDTO.getPrice()));

    verify(orderService, times(1)).createOrder(any());
  }

  @Test
  void getOrderById() throws Exception {
    when(orderService.getOrderById(1L)).thenReturn(orderResponse);

    mockMvc.perform(get("/api/orders/{id}", 1L))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(orderResponse.getId()))
        .andExpect(jsonPath("$.status").value(orderResponse.getStatus().toString()))
        .andExpect(jsonPath("$.items[0].productId").value(orderItemDTO.getProductId()))
        .andExpect(jsonPath("$.items[0].quantity").value(orderItemDTO.getQuantity()))
        .andExpect(jsonPath("$.items[0].price").value(orderItemDTO.getPrice()));

    verify(orderService, times(1)).getOrderById(1L);
  }

  @Test
  void getAllOrders() throws Exception {
    List<OrderResponse> orderResponses = Arrays.asList(orderResponse);
    when(orderService.getAllOrders()).thenReturn(orderResponses);

    mockMvc.perform(get("/api/orders"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].id").value(orderResponse.getId()))
        .andExpect(jsonPath("$[0].status").value(orderResponse.getStatus().toString()))
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