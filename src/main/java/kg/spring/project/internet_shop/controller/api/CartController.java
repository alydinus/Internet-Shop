package kg.spring.project.internet_shop.controller.api;

import kg.spring.project.internet_shop.dto.payload.request.CartItemRequest;
import kg.spring.project.internet_shop.dto.payload.request.CartItemUpdateRequest;
import kg.spring.project.internet_shop.dto.payload.response.CartResponse;
import kg.spring.project.internet_shop.mapper.CartMapper;
import kg.spring.project.internet_shop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

  private final CartService cartService;
  private final CartMapper cartMapper;

  @GetMapping("/{id}")
  public ResponseEntity<CartResponse> getCart(@PathVariable Long id) {
    return new ResponseEntity<>(cartMapper.toDTO(cartService.getCart(id)), HttpStatus.OK);
  }

  @PostMapping("/add")
  public ResponseEntity<CartResponse> addToCart(@RequestBody CartItemRequest cartItemRequest) {
    return new ResponseEntity<>(cartMapper.toDTO(
        cartService.addToCart(cartItemRequest.getCartId(), cartItemRequest.getProductId(),
            cartItemRequest.getQuantity())),
        HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<CartResponse> updateCartItemQuantity(
      @RequestBody CartItemRequest cartItemRequest) {
    return new ResponseEntity<>(
        cartMapper.toDTO(
            cartService.updateCart(cartItemRequest.getCartId(), cartItemRequest.getProductId(),
                cartItemRequest.getQuantity())), HttpStatus.OK);
  }

  @PutMapping("/remove")
  public ResponseEntity<CartResponse> removeFromCart(@RequestParam Long cartId,
      @RequestParam Long productId) {
    return new ResponseEntity<>(cartMapper.toDTO(
        cartService.removeFromCart(cartId, productId)), HttpStatus.OK);
  }

  @DeleteMapping("/clear/{id}")
  public ResponseEntity<String> clearCart(@PathVariable Long id) {
    return new ResponseEntity<>(cartService.clearCart(id), HttpStatus.OK);
  }
}
