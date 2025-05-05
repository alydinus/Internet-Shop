package kg.spring.project.internet_shop.mapper.impl;

import java.util.ArrayList;
import java.util.List;
import kg.spring.project.internet_shop.dto.CartItemDTO;
import kg.spring.project.internet_shop.dto.payload.response.CartResponse;
import kg.spring.project.internet_shop.entity.Cart;
import kg.spring.project.internet_shop.entity.CartItem;
import kg.spring.project.internet_shop.mapper.CartMapper;
import org.springframework.stereotype.Component;

@Component
public class CartMapperImpl implements CartMapper {

  public CartResponse toDTO(Cart cart) {
    CartResponse cartResponse = new CartResponse();
    cartResponse.setId(cart.getId());
    cartResponse.setUserId(cart.getUser().getId());
    List<CartItem> items = cart.getItems();
    List<CartItemDTO> cartItemDTOs = new ArrayList<>();
    for (CartItem item : items) {
      CartItemDTO cartItemDTO = new CartItemDTO();
      cartItemDTO.setProductName(item.getProduct().getName());
      cartItemDTO.setQuantity(item.getQuantity());
      cartItemDTO.setPrice(item.getPrice());
      cartItemDTOs.add(cartItemDTO);
    }

    cartResponse.setItems(cartItemDTOs);

    return cartResponse;

  }
}
