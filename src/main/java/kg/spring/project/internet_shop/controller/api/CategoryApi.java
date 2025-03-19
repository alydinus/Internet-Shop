package kg.spring.project.internet_shop.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.spring.project.internet_shop.dto.CategoryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Category API", description = "Управление категориями товаров")
public interface CategoryApi {

  @Operation(summary = "Получить все категории", description = "Возвращает список всех категорий")
  @ApiResponse(responseCode = "200", description = "Список категорий", content = @Content(schema = @Schema(implementation = CategoryDTO.class)))
  @GetMapping
  ResponseEntity<List<CategoryDTO>> getAllCategories();

  @Operation(summary = "Получить категорию по ID", description = "Возвращает категорию по её ID")
  @ApiResponse(responseCode = "200", description = "Категория найдена", content = @Content(schema = @Schema(implementation = CategoryDTO.class)))
  @ApiResponse(responseCode = "404", description = "Категория не найдена")
  @GetMapping("/{id}")
  ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id);

  @Operation(summary = "Получить категорию по названию", description = "Возвращает категорию по её названию")
  @ApiResponse(responseCode = "200", description = "Категория найдена", content = @Content(schema = @Schema(implementation = CategoryDTO.class)))
  @ApiResponse(responseCode = "404", description = "Категория не найдена")
  @GetMapping("/name/{name}")
  ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String name);

  @Operation(summary = "Создать новую категорию", description = "Добавляет новую категорию")
  @ApiResponse(responseCode = "201", description = "Категория создана", content = @Content(schema = @Schema(implementation = CategoryDTO.class)))
  @PostMapping
  ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO);

  @Operation(summary = "Обновить категорию", description = "Обновляет информацию о категории")
  @ApiResponse(responseCode = "200", description = "Категория обновлена", content = @Content(schema = @Schema(implementation = CategoryDTO.class)))
  @ApiResponse(responseCode = "404", description = "Категория не найдена")
  @PutMapping("/{id}")
  ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id,
      @RequestBody CategoryDTO categoryDTO);

  @Operation(summary = "Удалить категорию", description = "Удаляет категорию по ID")
  @ApiResponse(responseCode = "200", description = "Категория удалена")
  @ApiResponse(responseCode = "404", description = "Категория не найдена")
  @DeleteMapping("/{id}")
  ResponseEntity<String> deleteCategory(@PathVariable Long id);
}
