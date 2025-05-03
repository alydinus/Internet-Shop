package kg.spring.project.internet_shop.service;

import kg.spring.project.internet_shop.dto.CartDTO;

public interface CartService {

  CartDTO getCart(Long id);

  CartDTO addToCart(Long productId, int quantity);

  CartDTO updateCart(Long productId, int quantity);

  CartDTO removeFromCart(Long productId);

  String clearCart();

}
