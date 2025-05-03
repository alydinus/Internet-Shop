package kg.spring.project.internet_shop.mapper.impl;

import java.util.ArrayList;
import java.util.List;
import kg.spring.project.internet_shop.dto.OrderItemDTO;
import kg.spring.project.internet_shop.entity.OrderItem;
import kg.spring.project.internet_shop.mapper.OrderItemMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderItemMapperImpl implements OrderItemMapper {

  public OrderItem toEntity(OrderItemDTO dto) {
    OrderItem orderItem = new OrderItem();
    orderItem.setId(dto.getProductId());
    orderItem.setQuantity(dto.getQuantity());
    orderItem.setPrice(dto.getPrice());
    return orderItem;
  }

  public List<OrderItemDTO> toOrderItemDTOList(List<OrderItem> orderItems) {
    List<OrderItemDTO> orderItemDTOs = new ArrayList<>();
    for (OrderItem orderItem : orderItems) {
      OrderItemDTO orderItemDTO = toOrderItemDTO(orderItem);
      orderItemDTOs.add(orderItemDTO);
    }
    return orderItemDTOs;
  }

  public OrderItemDTO toOrderItemDTO(OrderItem orderItem) {
    OrderItemDTO dto = new OrderItemDTO();
    dto.setProductId(orderItem.getProduct().getId());
    dto.setId(orderItem.getId());
    dto.setQuantity(orderItem.getQuantity());
    dto.setPrice(orderItem.getPrice());
    return dto;
  }
}
