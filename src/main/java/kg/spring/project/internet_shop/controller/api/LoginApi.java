package kg.spring.project.internet_shop.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.spring.project.internet_shop.dto.UserLoginDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Login API", description = "Аутентификация пользователей")
public interface LoginApi {

  @Operation(
      summary = "Аутентификация пользователя",
      description = "Пользователь вводит свои учетные данные для входа в систему")
  @ApiResponse(
      responseCode = "200",
      description = "Успешная аутентификация")
  @PostMapping
  String login(@RequestBody UserLoginDTO userLoginDTO);
}
