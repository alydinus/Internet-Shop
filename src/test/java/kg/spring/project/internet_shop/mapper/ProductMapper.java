package kg.spring.project.internet_shop.mapper;

import kg.spring.project.internet_shop.dto.CategoryDTO;
import kg.spring.project.internet_shop.dto.ProductDTO;
import kg.spring.project.internet_shop.entity.Category;
import kg.spring.project.internet_shop.entity.Product;
import kg.spring.project.internet_shop.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductMapperTest {

  @Mock
  private CategoryMapper categoryMapper;

  @Mock
  private CategoryService categoryService;

  @InjectMocks
  private ProductMapper productMapper;

  private Product product;
  private ProductDTO productDTO;
  private Category category;
  private CategoryDTO categoryDTO;

  @BeforeEach
  void setUp() {
    category = new Category();
    category.setId(1L);
    category.setName("Electronics");

    categoryDTO = new CategoryDTO();
    categoryDTO.setId(1L);
    categoryDTO.setName("Electronics");

    product = new Product();
    product.setId(1L);
    product.setName("Laptop");
    product.setPrice(1000.0);
    product.setDescription("A high-performance laptop");
    product.setStockQuantity(10);
    product.setCategory(category);

    productDTO = new ProductDTO();
    productDTO.setId(1L);
    productDTO.setName("Laptop");
    productDTO.setPrice(1000.0);
    productDTO.setDescription("A high-performance laptop");
    productDTO.setStockQuantity(10);
    productDTO.setCategoryName("Electronics");
  }

  @Test
  void toProductDTO() {
    when(categoryMapper.toCategoryDTO(category)).thenReturn(categoryDTO);

    ProductDTO result = productMapper.toProductDTO(product);

    assertNotNull(result);
    assertEquals(product.getId(), result.getId());
    assertEquals(product.getName(), result.getName());
    assertEquals(product.getPrice(), result.getPrice());
    assertEquals(product.getDescription(), result.getDescription());
    assertEquals(product.getStockQuantity(), result.getStockQuantity());
    assertEquals(categoryDTO.getName(), result.getCategoryName());

    verify(categoryMapper, times(1)).toCategoryDTO(category);
    verify(categoryService, never()).getCategoryByName(anyString());
  }


  @Test
  void toProduct() {
    when(categoryService.getCategoryByName("Electronics")).thenReturn(categoryDTO);
    when(categoryMapper.toCategory(categoryDTO)).thenReturn(category);

    Product result = productMapper.toProduct(productDTO);

    assertNotNull(result);
    assertEquals(productDTO.getId(), result.getId());
    assertEquals(productDTO.getName(), result.getName());
    assertEquals(productDTO.getPrice(), result.getPrice());
    assertEquals(productDTO.getDescription(), result.getDescription());
    assertEquals(productDTO.getStockQuantity(), result.getStockQuantity());
    assertEquals(category, result.getCategory());

    verify(categoryService, times(1)).getCategoryByName("Electronics");
    verify(categoryMapper, times(1)).toCategory(categoryDTO);
  }


  @Test
  void toDtoList() {
    Product product2 = new Product();
    product2.setId(2L);
    product2.setName("Phone");
    product2.setPrice(500.0);
    product2.setDescription("A smartphone");
    product2.setStockQuantity(20);
    product2.setCategory(category);

    ProductDTO productDTO2 = new ProductDTO();
    productDTO2.setId(2L);
    productDTO2.setName("Phone");
    productDTO2.setPrice(500.0);
    productDTO2.setDescription("A smartphone");
    productDTO2.setStockQuantity(20);
    productDTO2.setCategoryName("Electronics");

    List<Product> products = Arrays.asList(product, product2);

    when(categoryMapper.toCategoryDTO(category)).thenReturn(categoryDTO);

    List<ProductDTO> result = productMapper.toDtoList(products);

    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals(product.getId(), result.get(0).getId());
    assertEquals(product.getName(), result.get(0).getName());
    assertEquals(product.getPrice(), result.get(0).getPrice());
    assertEquals(product.getDescription(), result.get(0).getDescription());
    assertEquals(product.getStockQuantity(), result.get(0).getStockQuantity());
    assertEquals(categoryDTO.getName(), result.get(0).getCategoryName());

    assertEquals(product2.getId(), result.get(1).getId());
    assertEquals(product2.getName(), result.get(1).getName());
    assertEquals(product2.getPrice(), result.get(1).getPrice());
    assertEquals(product2.getDescription(), result.get(1).getDescription());
    assertEquals(product2.getStockQuantity(), result.get(1).getStockQuantity());
    assertEquals(categoryDTO.getName(), result.get(1).getCategoryName());

    verify(categoryMapper, times(2)).toCategoryDTO(category);
    verify(categoryService, never()).getCategoryByName(anyString());
  }

  @Test
  void toEntityList() {
    ProductDTO productDTO2 = new ProductDTO();
    productDTO2.setId(2L);
    productDTO2.setName("Phone");
    productDTO2.setPrice(500.0);
    productDTO2.setDescription("A smartphone");
    productDTO2.setStockQuantity(20);
    productDTO2.setCategoryName("Electronics");

    List<ProductDTO> productDTOs = Arrays.asList(productDTO, productDTO2);

    when(categoryService.getCategoryByName("Electronics")).thenReturn(categoryDTO);
    when(categoryMapper.toCategory(categoryDTO)).thenReturn(category);

    List<Product> result = productMapper.toEntityList(productDTOs);

    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals(productDTO.getId(), result.get(0).getId());
    assertEquals(productDTO.getName(), result.get(0).getName());
    assertEquals(productDTO2.getId(), result.get(1).getId());
    assertEquals(productDTO2.getName(), result.get(1).getName());

    verify(categoryService, times(2)).getCategoryByName("Electronics");
    verify(categoryMapper, times(2)).toCategory(categoryDTO);
  }

}