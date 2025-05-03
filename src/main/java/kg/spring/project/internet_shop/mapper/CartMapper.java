package kg.spring.project.internet_shop.mapper;

import kg.spring.project.internet_shop.dto.CartDTO;
import kg.spring.project.internet_shop.entity.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {
  Cart toEntity(Cart cart);
  CartDTO toDTO(Cart cart);
}
