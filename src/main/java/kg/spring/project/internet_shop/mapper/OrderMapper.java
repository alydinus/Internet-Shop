package kg.spring.project.internet_shop.mapper;

import kg.spring.project.internet_shop.dto.OrderDTO;
import kg.spring.project.internet_shop.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

  OrderDTO toOrderDTO(Order order);

  Order toOrder(OrderDTO dto);

}
