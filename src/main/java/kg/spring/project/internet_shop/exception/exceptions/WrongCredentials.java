package kg.spring.project.internet_shop.exception.exceptions;

public class WrongCredentials extends RuntimeException {

  public WrongCredentials(String message) {
    super(message);
  }
}
