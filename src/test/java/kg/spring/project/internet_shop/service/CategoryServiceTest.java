package kg.spring.project.internet_shop.service.impl;

import kg.spring.project.internet_shop.dto.CategoryDTO;
import kg.spring.project.internet_shop.entity.Category;
import kg.spring.project.internet_shop.entity.Product;
import kg.spring.project.internet_shop.mapper.CategoryMapper;
import kg.spring.project.internet_shop.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

  @Mock
  private CategoryRepository categoryRepository;

  @Mock
  private CategoryMapper categoryMapper;

  @InjectMocks
  private CategoryServiceImpl categoryService;

  private Category category;
  private CategoryDTO categoryDTO;
  private List<Product> products;

  @BeforeEach
  void setUp() {
    category = new Category();
    category.setId(1L);
    category.setName("Electronics");

    products = Arrays.asList(new Product(), new Product());
    category.setProducts(products);

    categoryDTO = new CategoryDTO();
    categoryDTO.setId(1L);
    categoryDTO.setName("Electronics");
  }

  @Test
  void getAllCategories() {
    List<Category> categories = Arrays.asList(category);
    List<CategoryDTO> categoryDTOs = Arrays.asList(categoryDTO);

    when(categoryRepository.findAll()).thenReturn(categories);
    when(categoryMapper.toDtoList(categories)).thenReturn(categoryDTOs);

    List<CategoryDTO> result = categoryService.getAllCategories();

    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals(categoryDTO, result.get(0));
    verify(categoryRepository, times(1)).findAll();
    verify(categoryMapper, times(1)).toDtoList(categories);
  }

  @Test
  void getCategoryById() {
    when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
    when(categoryMapper.toCategoryDTO(category)).thenReturn(categoryDTO);

    CategoryDTO result = categoryService.getCategoryById(1L);

    assertNotNull(result);
    assertEquals(categoryDTO, result);
    verify(categoryRepository, times(1)).findById(1L);
    verify(categoryMapper, times(1)).toCategoryDTO(category);
  }



  @Test
  void getCategoryByName() {
    when(categoryRepository.findByName("Electronics")).thenReturn(Optional.of(category));
    when(categoryMapper.toCategoryDTO(category)).thenReturn(categoryDTO);

    CategoryDTO result = categoryService.getCategoryByName("Electronics");

    assertNotNull(result);
    assertEquals(categoryDTO, result);
    verify(categoryRepository, times(1)).findByName("Electronics");
    verify(categoryMapper, times(1)).toCategoryDTO(category);
  }


  @Test
  void createCategory() {
    when(categoryMapper.toCategory(categoryDTO)).thenReturn(category);
    when(categoryRepository.save(category)).thenReturn(category);
    when(categoryMapper.toCategoryDTO(category)).thenReturn(categoryDTO);

    CategoryDTO result = categoryService.createCategory(categoryDTO);

    assertNotNull(result);
    assertEquals(categoryDTO, result);
    verify(categoryMapper, times(1)).toCategory(categoryDTO);
    verify(categoryRepository, times(1)).save(category);
    verify(categoryMapper, times(1)).toCategoryDTO(category);
  }

  @Test
  void updateCategory() {
    when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
    when(categoryRepository.save(category)).thenReturn(category);
    when(categoryMapper.toCategoryDTO(category)).thenReturn(categoryDTO);

    CategoryDTO result = categoryService.updateCategory(1L, categoryDTO);

    assertNotNull(result);
    assertEquals(categoryDTO, result);
    assertEquals("Electronics", category.getName());
    assertEquals(products, category.getProducts());
    verify(categoryRepository, times(1)).findById(1L);
    verify(categoryRepository, times(1)).save(category);
    verify(categoryMapper, times(1)).toCategoryDTO(category);
  }

  @Test
  void deleteCategory() {
    categoryService.deleteCategory(1L);

    verify(categoryRepository, times(1)).deleteById(1L);
  }
}