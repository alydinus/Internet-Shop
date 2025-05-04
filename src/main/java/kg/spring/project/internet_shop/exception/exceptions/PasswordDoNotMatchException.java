package kg.spring.project.internet_shop.exception.exceptions;

public class PasswordDoNotMatchException extends RuntimeException {

  public PasswordDoNotMatchException(String message) {
    super(message);
  }
}
