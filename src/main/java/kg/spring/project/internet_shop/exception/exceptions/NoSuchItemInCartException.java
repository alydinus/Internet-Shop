package kg.spring.project.internet_shop.exception.exceptions;

public class NoSuchItemInCartException extends RuntimeException {

  public NoSuchItemInCartException(String message) {
    super(message);
  }
}
