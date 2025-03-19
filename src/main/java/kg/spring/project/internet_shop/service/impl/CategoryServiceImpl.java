package kg.spring.project.internet_shop.service.impl;

import java.util.List;
import kg.spring.project.internet_shop.dto.CategoryDTO;
import kg.spring.project.internet_shop.entity.Category;
import kg.spring.project.internet_shop.entity.Product;
import kg.spring.project.internet_shop.mapper.CategoryMapper;
import kg.spring.project.internet_shop.repository.CategoryRepository;
import kg.spring.project.internet_shop.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final CategoryMapper categoryMapper;

  public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
    this.categoryRepository = categoryRepository;
    this.categoryMapper = categoryMapper;
  }

  public List<CategoryDTO> getAllCategories() {
    List<Category> categories = categoryRepository.findAll();
    if (categories.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Categories not found");
    }
    return categoryMapper.toDtoList(categories);
  }

  public CategoryDTO getCategoryById(Long id) {
    Category category = categoryRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
            "Category with id " + id + " not found"));

    return categoryMapper.toCategoryDTO(category);
  }

  public CategoryDTO getCategoryByName(String name) {
    return categoryMapper.toCategoryDTO(categoryRepository.findByName(name)
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Category with name " + name + " not found")));
  }

  public CategoryDTO createCategory(CategoryDTO categoryDTO) {
    return categoryMapper.toCategoryDTO(
        categoryRepository.save(categoryMapper.toCategory(categoryDTO)));
  }

  public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
    Category category = categoryRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
            "Category with id " + id + " not found"));
    category.setName(categoryDTO.getName());
    List<Product> products = category.getProducts();
    category.setProducts(products);
    return categoryMapper.toCategoryDTO(categoryRepository.save(category));
  }

  public void deleteCategory(Long id) {
    categoryRepository.deleteById(id);
  }
}
