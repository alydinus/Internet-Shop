package kg.spring.project.internet_shop.controller.api.controllers;

import kg.spring.project.internet_shop.controller.api.CartApi;
import kg.spring.project.internet_shop.dto.CartDTO;
import kg.spring.project.internet_shop.mapper.CartMapper;
import kg.spring.project.internet_shop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController implements CartApi {

  private final CartService cartService;
  private final CartMapper cartMapper;

  public ResponseEntity<CartDTO> getCart(Long id) {
    return new ResponseEntity<>(cartMapper.toDTO(cartService.getCart(id)), HttpStatus.OK);
  }

  public ResponseEntity<CartDTO> addToCart(Long cartId, Long productId, int quantity) {
    return new ResponseEntity<>(cartMapper.toDTO(cartService.addToCart(cartId, productId, quantity)), HttpStatus.OK);
  }

  public ResponseEntity<CartDTO> updateCartItemQuantity(Long cartId, Long productId, int quantity) {
    return new ResponseEntity<>(cartMapper.toDTO(cartService.updateCart(cartId, productId, quantity)), HttpStatus.OK);
  }

  public ResponseEntity<CartDTO> removeFromCart(Long cartId, Long productId) {
    return new ResponseEntity<>(cartMapper.toDTO(cartService.removeFromCart(cartId, productId)), HttpStatus.OK);
  }

  public ResponseEntity<String> clearCart(Long cartId) {
    return new ResponseEntity<>(cartService.clearCart(cartId), HttpStatus.OK);
  }
}
