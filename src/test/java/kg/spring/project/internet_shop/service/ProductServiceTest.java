package kg.spring.project.internet_shop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import kg.spring.project.internet_shop.dto.CategoryDTO;
import kg.spring.project.internet_shop.dto.ProductDTO;
import kg.spring.project.internet_shop.entity.Category;
import kg.spring.project.internet_shop.entity.Product;
import kg.spring.project.internet_shop.mapper.CategoryMapper;
import kg.spring.project.internet_shop.mapper.ProductMapper;
import kg.spring.project.internet_shop.repository.ProductRepository;
import kg.spring.project.internet_shop.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

  @Mock
  private ProductRepository productRepository;

  @Mock
  private ProductMapper productMapper;

  @Mock
  private CategoryMapper categoryMapper;

  @Mock
  private CategoryService categoryService;

  @InjectMocks
  private ProductServiceImpl productService;

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
  void getAllProducts_Success_ReturnsListOfProducts() {
    List<Product> products = Arrays.asList(product);
    List<ProductDTO> productDTOs = Arrays.asList(productDTO);
    when(productRepository.findAll()).thenReturn(products);
    when(productMapper.toProductDTO(product)).thenReturn(productDTO);

    List<ProductDTO> result = productService.getAllProducts();

    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals(productDTO, result.get(0));
    verify(productRepository, times(1)).findAll();
    verify(productMapper, times(1)).toProductDTO(product);
  }

  @Test
  void getProductById() {
    when(productRepository.findById(1L)).thenReturn(Optional.of(product));
    when(productMapper.toProductDTO(product)).thenReturn(productDTO);

    ProductDTO result = productService.getProductById(1L);

    assertNotNull(result);
    assertEquals(productDTO, result);
    verify(productRepository, times(1)).findById(1L);
    verify(productMapper, times(1)).toProductDTO(product);
  }

  @Test
  void createProduct() {
    when(productMapper.toProduct(productDTO)).thenReturn(product);
    when(categoryService.getCategoryByName("Electronics")).thenReturn(categoryDTO);
    when(categoryMapper.toCategory(categoryDTO)).thenReturn(category);
    when(productRepository.save(any(Product.class))).thenAnswer(invocation -> {
      Product savedProduct = invocation.getArgument(0);
      savedProduct.setId(1L);
      return savedProduct;
    });
    when(productMapper.toProductDTO(any(Product.class))).thenReturn(productDTO);

    ProductDTO result = productService.createProduct(productDTO);

    assertNotNull(result);
    assertEquals(productDTO, result);
    verify(productMapper, times(1)).toProduct(productDTO);
    verify(categoryService, times(1)).getCategoryByName("Electronics");
    verify(categoryMapper, times(1)).toCategory(categoryDTO);
    verify(productRepository, times(1)).save(any(Product.class));
    verify(productMapper, times(1)).toProductDTO(any(Product.class));
  }

  @Test
  void updateProduct() {
    when(productRepository.findById(1L)).thenReturn(Optional.of(product));
    when(categoryService.getCategoryByName("Electronics")).thenReturn(categoryDTO);
    when(categoryMapper.toCategory(categoryDTO)).thenReturn(category);
    when(productRepository.save(product)).thenReturn(product);
    when(productMapper.toProductDTO(product)).thenReturn(productDTO);

    ProductDTO result = productService.updateProduct(1L, productDTO);

    assertNotNull(result);
    assertEquals(productDTO, result);
    assertEquals("Laptop", product.getName());
    assertEquals(1000.0, product.getPrice());
    assertEquals("A high-performance laptop", product.getDescription());
    assertEquals(10, product.getStockQuantity());
    assertEquals(category, product.getCategory());
    verify(productRepository, times(1)).findById(1L);
    verify(categoryService, times(1)).getCategoryByName("Electronics");
    verify(categoryMapper, times(1)).toCategory(categoryDTO);
    verify(productRepository, times(1)).save(product);
    verify(productMapper, times(1)).toProductDTO(product);
  }

  @Test
  void updateProductPrice() {
    ProductDTO priceUpdateDTO = new ProductDTO();
    priceUpdateDTO.setPrice(1500.0);

    when(productRepository.findById(1L)).thenReturn(Optional.of(product));
    when(productRepository.save(product)).thenReturn(product);
    when(productMapper.toProductDTO(product)).thenReturn(productDTO);

    ProductDTO result = productService.updateProductPrice(1L, priceUpdateDTO);

    assertNotNull(result);
    assertEquals(productDTO, result);
    assertEquals(1500.0, product.getPrice());
    verify(productRepository, times(1)).findById(1L);
    verify(productRepository, times(1)).save(product);
    verify(productMapper, times(1)).toProductDTO(product);
  }

  @Test
  void deleteProduct_Success_DeletesProduct() {
    productService.deleteProduct(1L);

    verify(productRepository, times(1)).deleteById(1L);
  }
}