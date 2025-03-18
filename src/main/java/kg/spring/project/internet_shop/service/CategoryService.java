package kg.spring.project.internet_shop.service;

import java.util.List;
import kg.spring.project.dto.CategoryDTO;

public interface CategoryService {
  List<CategoryDTO> getAllCategories();
  CategoryDTO getCategoryById(Long id);
  CategoryDTO getCategoryByName(String name);
  CategoryDTO createCategory(CategoryDTO categoryDTO);
  CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO);
  void deleteCategory(Long id);
}
