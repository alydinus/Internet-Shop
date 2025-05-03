package kg.spring.project.internet_shop.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.spring.project.internet_shop.dto.ProductDTO;
import kg.spring.project.internet_shop.dto.payload.request.ProductRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Product API", description = "Управление товарами")
public interface ProductApi {

  @Operation(summary = "Получить все товары", description = "Возвращает список всех товаров")
  @ApiResponse(responseCode = "200", description = "Список товаров", content = @Content(schema = @Schema(implementation = ProductDTO.class)))
  @GetMapping
  ResponseEntity<List<ProductDTO>> getAllProducts();

  @Operation(summary = "Получить товар по ID", description = "Возвращает товар по его ID")
  @ApiResponse(responseCode = "200", description = "Товар найден", content = @Content(schema = @Schema(implementation = ProductDTO.class)))
  @ApiResponse(responseCode = "404", description = "Товар не найден")
  @GetMapping("/{id}")
  ResponseEntity<ProductDTO> getProductById(@PathVariable Long id);

  @Operation(summary = "Создать новый товар", description = "Добавляет новый товар")
  @ApiResponse(responseCode = "201", description = "Товар создан", content = @Content(schema = @Schema(implementation = ProductDTO.class)))
  @PostMapping
  ResponseEntity<ProductDTO> createProduct(@RequestBody ProductRequest productDTO);

  @Operation(summary = "Обновить товар", description = "Обновляет товар")
  @ApiResponse(responseCode = "200", description = "Товар обновлен", content = @Content(schema = @Schema(implementation = ProductDTO.class)))
  @ApiResponse(responseCode = "404", description = "Товар не найден")
  @PutMapping("/{id}")
  ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO);

  @Operation(summary = "Удалить товар", description = "Удаляет товар по ID")
  @ApiResponse(responseCode = "204", description = "Товар удален")
  @ApiResponse(responseCode = "404", description = "Товар не найден")
  @DeleteMapping("/{id}")
  ResponseEntity<String> deleteProduct(@PathVariable Long id);
}

