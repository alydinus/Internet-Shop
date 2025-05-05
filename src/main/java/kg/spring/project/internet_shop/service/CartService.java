package kg.spring.project.internet_shop.service;

import java.util.List;
import kg.spring.project.internet_shop.entity.Cart;

public interface CartService {

  Cart getCart(Long id);

  Cart addToCart(Long cartId, Long productId, int quantity);

  Cart createCart(Long userId);

  Cart updateCart(Long cartId, Long productId, int quantity);

  Cart removeFromCart(Long cartId, Long productId);

  String clearCart(Long cartId);

}
