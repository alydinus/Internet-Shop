package kg.spring.project.internet_shop.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.spring.project.internet_shop.dto.payload.request.CartItemRequest;
import kg.spring.project.internet_shop.dto.payload.response.CartResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Cart API", description = "Управление корзиной")
public interface CartApi {

  @Operation(summary = "Получить корзину", description = "Возвращает текущую корзину пользователя")
  @ApiResponse(responseCode = "200", description = "Корзина найдена")
  @GetMapping("/{id}")
  ResponseEntity<CartResponse> getCart(@PathVariable Long id);

  @PostMapping
  @Operation(summary = "Добавить товар в корзину", description = "Добавляет товар в корзину пользователя")
  @ApiResponse(responseCode = "200", description = "Товар добавлен в корзину")
  ResponseEntity<CartResponse> addToCart(@Validated @RequestBody CartItemRequest cartItemRequest);

  @PutMapping
  @Operation(summary = "Обновить количество товара в корзине", description = "Обновляет количество товара в корзине пользователя")
  @ApiResponse(responseCode = "200", description = "Количество товара обновлено")
  ResponseEntity<CartResponse> updateCartItemQuantity(@RequestBody CartItemRequest cartItemRequest);

  @Operation(summary = "Удалить товар из корзины", description = "Удаляет товар из корзины пользователя")
  @ApiResponse(responseCode = "200", description = "Товар удален из корзины")
  @PostMapping("/remove")
  ResponseEntity<CartResponse> removeFromCart(@RequestBody CartItemRequest cartItemRequest);

  @Operation(summary = "Очистить корзину", description = "Удаляет все товары из корзины пользователя")
  @ApiResponse(responseCode = "200", description = "Корзина очищена")
  @PostMapping("/clear")
  ResponseEntity<String> clearCart(@RequestBody CartItemRequest cartItemRequest);
}
