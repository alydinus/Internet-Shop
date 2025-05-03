package kg.spring.project.internet_shop.service;

import kg.spring.project.internet_shop.entity.Cart;

public interface CartService {

  Cart getCart(Long id);

  Cart addToCart(Long cartId, Long productId, int quantity);

  Cart updateCart(Long cartId, Long productId, int quantity);

  Cart removeFromCart(Long cartId, Long productId);

  String clearCart(Long cartId);

}
