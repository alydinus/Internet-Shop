package kg.spring.project.internet_shop.controller.api.controllers;

import kg.spring.project.internet_shop.controller.api.CartApi;
import kg.spring.project.internet_shop.dto.CartDTO;
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

  public ResponseEntity<CartDTO> getCart(Long id) {
    return new ResponseEntity<>(cartService.getCart(id), HttpStatus.OK);
  }

  public ResponseEntity<CartDTO> addToCart(Long productId, int quantity) {
    return new ResponseEntity<>(cartService.addToCart(productId, quantity), HttpStatus.OK);
  }

  public ResponseEntity<CartDTO> updateCart(Long productId, int quantity) {
    return new ResponseEntity<>(cartService.updateCart(productId, quantity), HttpStatus.OK);
  }

  public ResponseEntity<CartDTO> removeFromCart(Long productId) {
    return new ResponseEntity<>(cartService.removeFromCart(productId), HttpStatus.OK);
  }

  public ResponseEntity<String> clearCart() {
    return new ResponseEntity<>(cartService.clearCart(), HttpStatus.OK);
  }
}
