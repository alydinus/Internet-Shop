package kg.spring.project.internet_shop.controller.api.controllers;

import kg.spring.project.internet_shop.controller.api.CartApi;
import kg.spring.project.internet_shop.dto.payload.request.CartItemRequest;
import kg.spring.project.internet_shop.dto.payload.response.CartResponse;
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

  public ResponseEntity<CartResponse> getCart(Long id) {
    return new ResponseEntity<>(cartMapper.toDTO(cartService.getCart(id)), HttpStatus.OK);
  }

  public ResponseEntity<CartResponse> addToCart(CartItemRequest cartItemRequest) {
    return new ResponseEntity<>(cartMapper.toDTO(
        cartService.addToCart(cartItemRequest.getCartId(), cartItemRequest.getProductId(),
            cartItemRequest.getQuantity())),
        HttpStatus.OK);
  }

  public ResponseEntity<CartResponse> updateCartItemQuantity(CartItemRequest cartItemRequest) {
    return new ResponseEntity<>(
        cartMapper.toDTO(cartService.updateCart(cartItemRequest.getCartId(), cartItemRequest.getProductId(),
            cartItemRequest.getQuantity())), HttpStatus.OK);
  }

  public ResponseEntity<CartResponse> removeFromCart(CartItemRequest cartItemRequest) {
    return new ResponseEntity<>(cartMapper.toDTO(
        cartService.removeFromCart(cartItemRequest.getCartId(), cartItemRequest.getProductId())),
        HttpStatus.OK);
  }

  public ResponseEntity<String> clearCart(CartItemRequest cartItemRequest) {
    return new ResponseEntity<>(cartService.clearCart(cartItemRequest.getCartId()), HttpStatus.OK);
  }
}
