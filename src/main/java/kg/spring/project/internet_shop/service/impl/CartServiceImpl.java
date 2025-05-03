package kg.spring.project.internet_shop.service.impl;

import kg.spring.project.internet_shop.dto.CartDTO;
import kg.spring.project.internet_shop.entity.Cart;
import kg.spring.project.internet_shop.exception.exceptions.CartNotFoundException;
import kg.spring.project.internet_shop.mapper.CartMapper;
import kg.spring.project.internet_shop.repository.CartRepository;
import kg.spring.project.internet_shop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

  private final CartRepository cartRepository;
  private final CartMapper cartMapper;

  public CartDTO getCart(Long id) {
    try{
      Cart cart = cartRepository.findById(id)
          .orElseThrow(() -> new CartNotFoundException("Cart with id " + id + " not found"));
      return cartMapper.toDTO(cart);
    } catch (CartNotFoundException e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  public CartDTO addToCart(Long productId, int quantity) {
    return null;
  }

  public CartDTO updateCart(Long productId, int quantity) {
    return null;
  }

  public CartDTO removeFromCart(Long productId) {
    return null;
  }

  public String clearCart() {
    return "";
  }
}
