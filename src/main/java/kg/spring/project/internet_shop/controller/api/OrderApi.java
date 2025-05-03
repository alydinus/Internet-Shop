package kg.spring.project.internet_shop.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.spring.project.internet_shop.dto.payload.response.OrderResponse;
import kg.spring.project.internet_shop.dto.payload.request.OrderRequest;
import kg.spring.project.internet_shop.enums.OrderStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Order API", description = "Управление заказами")
public interface OrderApi {

  @Operation(summary = "Создать заказ", description = "Создает новый заказ")
  @ApiResponse(responseCode = "201", description = "Заказ успешно создан", content = @Content(schema = @Schema(implementation = OrderResponse.class)))
  @PostMapping
  ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest);

  @Operation(summary = "Получить заказ по ID", description = "Возвращает заказ по его ID")
  @ApiResponse(responseCode = "200", description = "Заказ найден", content = @Content(schema = @Schema(implementation = OrderResponse.class)))
  @ApiResponse(responseCode = "404", description = "Заказ не найден")
  @GetMapping("/{id}")
  ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id);

  @GetMapping("/user/{id}")
  @Operation(summary = "Получить все заказы пользователя", description = "Возвращает список всех заказов пользователя")
  @ApiResponse(responseCode = "200", description = "Список заказов пользователя", content = @Content(schema = @Schema(implementation = OrderResponse.class)))
  ResponseEntity<List<OrderResponse>> getAllOrdersByUserId(@PathVariable Long id);

  @Operation(summary = "Получить все заказы", description = "Возвращает список всех заказов")
  @ApiResponse(responseCode = "200", description = "Список заказов", content = @Content(schema = @Schema(implementation = OrderResponse.class)))
  @GetMapping
  ResponseEntity<List<OrderResponse>> getAllOrders();

  @Operation(summary = "Обновить статус заказа", description = "Изменяет статус заказа")
  @ApiResponse(responseCode = "200", description = "Статус заказа успешно обновлен")
  @ApiResponse(responseCode = "404", description = "Заказ не найден")
  @PatchMapping("/{id}/status")
  ResponseEntity<String> updateOrderStatus(@PathVariable Long id, @RequestParam OrderStatus status);

  @Operation(summary = "Удалить заказ", description = "Удаляет заказ по ID")
  @ApiResponse(responseCode = "204", description = "Заказ успешно удален")
  @ApiResponse(responseCode = "404", description = "Заказ не найден")
  @DeleteMapping("/{id}")
  ResponseEntity<String> deleteOrder(@PathVariable Long id);
}
