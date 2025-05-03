package kg.spring.project.internet_shop.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.spring.project.internet_shop.dto.UserRegisterDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Registration API", description = "Регистрация и аутентификация пользователей")
public interface RegistrationApi {

  @PostMapping
  @Operation(summary = "Регистрация нового пользователя", description = "Создает нового пользователя и отправляет ему письмо с подтверждением")
  @ApiResponse(responseCode = "201", description = "Пользователь успешно зарегистрирован")
  @ApiResponse(responseCode = "400", description = "Некорректные данные")
  ResponseEntity<String> registerUser(@RequestBody UserRegisterDTO userDTO);

}
