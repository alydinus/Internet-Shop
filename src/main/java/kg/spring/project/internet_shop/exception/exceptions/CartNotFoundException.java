package kg.spring.project.internet_shop.exception.exceptions;

public class CartNotFoundException extends RuntimeException {

  public CartNotFoundException(String message) {
    super(message);
  }
}
