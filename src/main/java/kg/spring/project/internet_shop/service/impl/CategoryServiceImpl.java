package kg.spring.project.internet_shop.service.impl;

import java.util.List;
import kg.spring.project.dto.CategoryDTO;
import kg.spring.project.internet_shop.entity.Category;
import kg.spring.project.internet_shop.entity.Product;
import kg.spring.project.internet_shop.repository.CategoryRepository;
import kg.spring.project.internet_shop.service.CategoryService;
import kg.spring.project.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final CategoryMapper categoryMapper;

  public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
    this.categoryRepository = categoryRepository;
    this.categoryMapper = categoryMapper;
  }

  public List<CategoryDTO> getAllCategories() {
    return categoryMapper.toDtoList(categoryRepository.findAll());
  }

  public CategoryDTO getCategoryById(Long id) {
    return categoryMapper.toCategoryDTO(categoryRepository.findById(id).orElse(null));
  }

  public CategoryDTO createCategory(CategoryDTO categoryDTO) {
    return categoryMapper.toCategoryDTO(
        categoryRepository.save(categoryMapper.toCategory(categoryDTO)));
  }

  public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
    Category category = categoryRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Category not found"));
    category.setName(categoryDTO.getName());
    List<Product> products = category.getProducts();
    category.setProducts(products);
    return categoryMapper.toCategoryDTO(categoryRepository.save(category));
  }

  public void deleteCategory(Long id) {
    categoryRepository.deleteById(id);
  }
}
