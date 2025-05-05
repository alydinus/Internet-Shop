package kg.spring.project.internet_shop.exception;

import io.jsonwebtoken.ExpiredJwtException;
import kg.spring.project.internet_shop.exception.exceptions.CartNotFoundException;
import kg.spring.project.internet_shop.exception.exceptions.NoSuchItemInCartException;
import kg.spring.project.internet_shop.exception.exceptions.OrderNotFoundException;
import kg.spring.project.internet_shop.exception.exceptions.PasswordDoNotMatchException;
import kg.spring.project.internet_shop.exception.exceptions.ProductNotFoundException;
import kg.spring.project.internet_shop.exception.exceptions.UserAlreadyExistsException;
import kg.spring.project.internet_shop.exception.exceptions.UserIsNotActivated;
import kg.spring.project.internet_shop.exception.exceptions.UserNotFoundException;
import kg.spring.project.internet_shop.exception.exceptions.WrongCredentials;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler
  public ResponseEntity<String> handleCartNotFoundException(CartNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
  }

  @ExceptionHandler
  public ResponseEntity<String> handleNoSuchCartException(NoSuchItemInCartException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
  }

  @ExceptionHandler
  public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
  }

  @ExceptionHandler
  public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
  }

  @ExceptionHandler
  public ResponseEntity<String> handleOrderNotFoundException(OrderNotFoundException e) {
    return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
  }

  @ExceptionHandler
  public ResponseEntity<String> handleWrongCredentialsException(WrongCredentials e) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
  }

  @ExceptionHandler
  public ResponseEntity<String> handlePasswordDoNotMatchException(PasswordDoNotMatchException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
  }

  @ExceptionHandler
  public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
  }

  @ExceptionHandler
  public ResponseEntity<String>handleJwtTokenIsExpiredOrInvalid(ExpiredJwtException e){
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
  }

  @ExceptionHandler
  public ResponseEntity<String> handleUserIsNotActivatedException(UserIsNotActivated e) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
  }
}
