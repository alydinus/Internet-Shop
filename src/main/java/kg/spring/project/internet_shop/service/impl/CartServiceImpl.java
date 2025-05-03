package kg.spring.project.internet_shop.service.impl;

import kg.spring.project.internet_shop.entity.Cart;
import kg.spring.project.internet_shop.entity.CartItem;
import kg.spring.project.internet_shop.entity.Product;
import kg.spring.project.internet_shop.exception.exceptions.CartNotFoundException;
import kg.spring.project.internet_shop.exception.exceptions.NoSuchItemInCartException;
import kg.spring.project.internet_shop.exception.exceptions.ProductNotFoundException;
import kg.spring.project.internet_shop.repository.CartRepository;
import kg.spring.project.internet_shop.service.CartService;
import kg.spring.project.internet_shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

  private final CartRepository cartRepository;
  private final ProductService productService;

  public Cart getCart(Long id) {
    try {
      return cartRepository.findById(id)
          .orElseThrow(() -> new CartNotFoundException("Cart with id " + id + " not found"));
    } catch (CartNotFoundException e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  public Cart addToCart(Long cartId, Long productId, int quantity) {
    try {
      Cart cart = getCart(cartId);
      CartItem cartItem = new CartItem();
      Product productById = productService.getProductById(productId);
      cartItem.setProduct(productById);
      cartItem.setQuantity(quantity);
      cartItem.setPrice(productById.getPrice());
      cartItem.setCart(cart);
      return cartRepository.save(cart);
    } catch (CartNotFoundException | ProductNotFoundException e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  public Cart updateCart(Long cartId, Long productId, int quantity) {
    try {
      Cart cart = getCart(cartId);
      CartItem cartItem = cart.getItems().stream()
          .filter(item -> item.getProduct().getId().equals(productId))
          .findFirst()
          .orElseThrow(() -> new ProductNotFoundException(
              "Product with id " + productId + " not found in cart"));
      cartItem.setQuantity(quantity);
      cartItem.setPrice(cartItem.getProduct().getPrice() * quantity);
      return cartRepository.save(cart);
    } catch (CartNotFoundException | ProductNotFoundException e) {
      System.out.println(e.getMessage());
      return null;
    }

  }

  public Cart removeFromCart(Long cartId, Long productId) {
    try {
      Cart cart = getCart(cartId);
      CartItem cartItem = cart.getItems().stream()
          .filter(item -> item.getProduct().getId().equals(productId))
          .findFirst()
          .orElseThrow(() -> new ProductNotFoundException(
              "Product with id " + productId + " not found in cart"));
      cart.getItems().remove(cartItem);
      return cartRepository.save(cart);
    } catch (CartNotFoundException | ProductNotFoundException | NoSuchItemInCartException e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  public String clearCart(Long cartId) {
    try {
      Cart cart = getCart(cartId);
      cart.getItems().clear();
      cartRepository.save(cart);
      return "Cart cleared successfully";
    } catch (CartNotFoundException e) {
      System.out.println(e.getMessage());
      return "Cart not found";
    }
  }
}
