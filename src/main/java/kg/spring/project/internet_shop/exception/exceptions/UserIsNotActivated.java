package kg.spring.project.internet_shop.exception.exceptions;

public class UserIsNotActivated extends RuntimeException {

  public UserIsNotActivated(String message) {
    super(message);
  }
}
