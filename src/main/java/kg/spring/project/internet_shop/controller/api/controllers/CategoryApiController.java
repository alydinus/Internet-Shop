package kg.spring.project.internet_shop.controller.api.controllers;

import java.util.List;
import kg.spring.project.internet_shop.controller.api.CategoryApi;
import kg.spring.project.internet_shop.dto.CategoryDTO;
import kg.spring.project.internet_shop.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class CategoryApiController implements CategoryApi {

  private final CategoryService categoryService;

  public CategoryApiController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  public ResponseEntity<List<CategoryDTO>> getAllCategories() {
    return ResponseEntity.ok(categoryService.getAllCategories());
  }

  public ResponseEntity<CategoryDTO> getCategoryById(Long id) {
      return ResponseEntity.ok(categoryService.getCategoryById(id));
  }

  public ResponseEntity<CategoryDTO> createCategory(CategoryDTO categoryDTO) {
    return ResponseEntity.ok(categoryService.createCategory(categoryDTO));
  }

  public ResponseEntity<CategoryDTO> updateCategory(Long id, CategoryDTO categoryDTO) {
    return ResponseEntity.ok(categoryService.updateCategory(id, categoryDTO));
  }

  public ResponseEntity<String> deleteCategory(Long id) {
    categoryService.deleteCategory(id);
    return ResponseEntity.ok("Category deleted");
  }

}
