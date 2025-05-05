package kg.spring.project.internet_shop.mapper;

import kg.spring.project.internet_shop.dto.payload.response.CartResponse;
import kg.spring.project.internet_shop.entity.Cart;

public interface CartMapper {

  CartResponse toDTO(Cart cart);

}
