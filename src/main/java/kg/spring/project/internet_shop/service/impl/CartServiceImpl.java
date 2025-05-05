package kg.spring.project.internet_shop.service.impl;

import java.util.List;
import kg.spring.project.internet_shop.entity.Cart;
import kg.spring.project.internet_shop.entity.CartItem;
import kg.spring.project.internet_shop.entity.Product;
import kg.spring.project.internet_shop.entity.User;
import kg.spring.project.internet_shop.exception.exceptions.CartNotFoundException;
import kg.spring.project.internet_shop.exception.exceptions.NoSuchItemInCartException;
import kg.spring.project.internet_shop.exception.exceptions.ProductNotFoundException;
import kg.spring.project.internet_shop.repository.CartItemRepository;
import kg.spring.project.internet_shop.repository.CartRepository;
import kg.spring.project.internet_shop.service.CartService;
import kg.spring.project.internet_shop.service.ProductService;
import kg.spring.project.internet_shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

  private final CartRepository cartRepository;
  private final CartItemRepository cartItemRepository;
  private final ProductService productService;
  private final UserService userService;

  public Cart getCart(Long id) {
    return cartRepository.findById(id)
        .orElseThrow(() -> new CartNotFoundException("Cart with id " + id + " not found"));

  }

  public Cart addToCart(Long cartId, Long productId, int quantity) {
    Cart cart = getCart(cartId);
    if (cart == null) {
      throw new CartNotFoundException("Cart with id " + cartId + " not found");
    }
    CartItem cartItem = new CartItem();
    Product productById = productService.getProductById(productId);
    if (productById == null) {
      throw new ProductNotFoundException("Product with id " + productId + " not found");
    }
    cartItem.setProduct(productById);
    cartItem.setQuantity(quantity);
    cartItem.setPrice(productById.getPrice());
    cartItem.setCart(cart);

    cartItemRepository.save(cartItem);
    return cartRepository.save(cart);
  }

  public Cart createCart(Long userId) {
    Cart cart = new Cart();
    User userById = userService.getUserById(userId);
    cart.setUser(userById);
    return cartRepository.save(cart);
  }

  public Cart updateCart(Long cartId, Long productId, int quantity) {
    Cart cart = getCart(cartId);
    if (cart == null) {
      throw new CartNotFoundException("Cart with id " + cartId + " not found");
    }
    CartItem cartItem = cart.getItems().stream()
        .filter(item -> item.getProduct().getId().equals(productId))
        .findFirst()
        .orElseThrow(() -> new ProductNotFoundException(
            "Product with id " + productId + " not found in cart"));
    cartItem.setQuantity(quantity);
    cartItem.setPrice(cartItem.getProduct().getPrice() * quantity);
    return cartRepository.save(cart);

  }

  public Cart removeFromCart(Long cartId, Long productId) {
    Cart cart = getCart(cartId);
    if (cart == null) {
      throw new CartNotFoundException("Cart with id " + cartId + " not found");
    }
    CartItem cartItem = cart.getItems().stream()
        .filter(item -> item.getProduct().getId().equals(productId))
        .findFirst()
        .orElseThrow(() -> new ProductNotFoundException(
            "Product with id " + productId + " not found in cart"));
    if (cartItem == null) {
      throw new NoSuchItemInCartException("Product with id " + productId + " not found in cart");
    }
    cart.getItems().remove(cartItem);
    return cartRepository.save(cart);
  }

  public String clearCart(Long cartId) {
    Cart cart = getCart(cartId);
    if (cart == null) {
      throw new CartNotFoundException("Cart with id " + cartId + " not found");
    }
    cart.getItems().clear();
    cartRepository.save(cart);
    return "Cart cleared successfully";
  }
}
