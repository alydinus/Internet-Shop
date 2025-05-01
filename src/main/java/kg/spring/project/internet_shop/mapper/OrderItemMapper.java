package kg.spring.project.internet_shop.mapper;

import kg.spring.project.internet_shop.dto.OrderItemDTO;
import kg.spring.project.internet_shop.entity.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

  OrderItemDTO toOrderItemDTO(OrderItem item);

  OrderItem toOrderItem(OrderItemDTO dto);

}
