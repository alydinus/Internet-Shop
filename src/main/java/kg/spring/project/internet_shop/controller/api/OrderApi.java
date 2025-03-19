package kg.spring.project.internet_shop.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.spring.project.internet_shop.dto.OrderDTO;
import kg.spring.project.internet_shop.enums.OrderStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Order API", description = "Управление заказами")
public interface OrderApi {

  @Operation(summary = "Создать заказ", description = "Создает новый заказ")
  @ApiResponse(responseCode = "201", description = "Заказ успешно создан", content = @Content(schema = @Schema(implementation = OrderDTO.class)))
  @PostMapping
  ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO);

  @Operation(summary = "Получить заказ по ID", description = "Возвращает заказ по его ID")
  @ApiResponse(responseCode = "200", description = "Заказ найден", content = @Content(schema = @Schema(implementation = OrderDTO.class)))
  @ApiResponse(responseCode = "404", description = "Заказ не найден")
  @GetMapping("/{id}")
  ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id);

  @Operation(summary = "Получить все заказы", description = "Возвращает список всех заказов")
  @ApiResponse(responseCode = "200", description = "Список заказов", content = @Content(schema = @Schema(implementation = OrderDTO.class)))
  @GetMapping
  ResponseEntity<List<OrderDTO>> getAllOrders();

  @Operation(summary = "Обновить статус заказа", description = "Изменяет статус заказа")
  @ApiResponse(responseCode = "200", description = "Статус заказа успешно обновлен")
  @ApiResponse(responseCode = "404", description = "Заказ не найден")
  @PatchMapping("/{id}/status")
  ResponseEntity<Void> updateOrderStatus(@PathVariable Long id, @RequestParam OrderStatus status);

  @Operation(summary = "Удалить заказ", description = "Удаляет заказ по ID")
  @ApiResponse(responseCode = "204", description = "Заказ успешно удален")
  @ApiResponse(responseCode = "404", description = "Заказ не найден")
  @DeleteMapping("/{id}")
  ResponseEntity<Void> deleteOrder(@PathVariable Long id);
}
