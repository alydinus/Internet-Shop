package kg.spring.project.internet_shop.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import kg.spring.project.internet_shop.dto.UserDTO;
import kg.spring.project.internet_shop.dto.payload.request.UserRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Tag(name = "User Admin API", description = "Управление пользователями администратором")
public interface UserAdminApi {

  @GetMapping
  @Operation(summary = "Получить всех пользователей", description = "Возвращает список всех пользователей")
  @ApiResponse(responseCode = "200", description = "Список пользователей")
  List<UserDTO> getAllUsers();

  @GetMapping("/{id}")
  @Operation(summary = "Получить пользователя по ID", description = "Возвращает пользователя по его ID")
  @ApiResponse(responseCode = "200", description = "Пользователь найден")
  @ApiResponse(responseCode = "404", description = "Пользователь не найден")
  UserDTO getUserById(@PathVariable Long id);

  @PostMapping
  @Operation(summary = "Создать нового пользователя", description = "Создает нового пользователя")
  @ApiResponse(responseCode = "201", description = "Пользователь успешно создан")
  @ApiResponse(responseCode = "400", description = "Некорректные данные")
  UserDTO createUser(UserRequest userDTO);

  @PutMapping("/{id}")
  @Operation(summary = "Обновить пользователя", description = "Обновляет информацию о пользователе")
  @ApiResponse(responseCode = "200", description = "Пользователь успешно обновлен")
  @ApiResponse(responseCode = "404", description = "Пользователь не найден")
  UserDTO updateUser(@PathVariable Long id, UserDTO userDTO);

  @Operation(summary = "Удалить пользователя", description = "Удаляет пользователя по ID")
  @ApiResponse(responseCode = "204", description = "Пользователь успешно удален")
  @ApiResponse(responseCode = "404", description = "Пользователь не найден")
  @DeleteMapping("/{id}")
  void deleteUser(@PathVariable Long id);

}
