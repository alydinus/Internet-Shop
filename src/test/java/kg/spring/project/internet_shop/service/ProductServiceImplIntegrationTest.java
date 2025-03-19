package kg.spring.project.internet_shop.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import kg.spring.project.internet_shop.dto.ProductDTO;
import kg.spring.project.internet_shop.entity.Category;
import kg.spring.project.internet_shop.entity.Product;
import kg.spring.project.internet_shop.repository.CategoryRepository;
import kg.spring.project.internet_shop.repository.ProductRepository;
import kg.spring.project.internet_shop.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceImplIntegrationTest {

  @Autowired
  private ProductServiceImpl productService;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  private Category category;

  @BeforeEach
  void setUp() {
    productRepository.deleteAll();
    categoryRepository.deleteAll();

    category = new Category();
    category.setName("Electronics");
    categoryRepository.save(category);
  }

  @Test
  void createProduct() {
    ProductDTO productDTO = new ProductDTO();
    productDTO.setName("Laptop");
    productDTO.setPrice(1000.0);
    productDTO.setDescription("A high-performance laptop");
    productDTO.setStockQuantity(10);
    productDTO.setCategoryName("Electronics");

    ProductDTO createdProduct = productService.createProduct(productDTO);

    assertNotNull(createdProduct);
    assertNotNull(createdProduct.getId());
    assertEquals("Laptop", createdProduct.getName());
    assertEquals(1000.0, createdProduct.getPrice());
    assertEquals("A high-performance laptop", createdProduct.getDescription());
    assertEquals(10, createdProduct.getStockQuantity());
    assertEquals("Electronics", createdProduct.getCategoryName());

    Product savedProduct = productRepository.findById(createdProduct.getId()).orElse(null);
    assertNotNull(savedProduct);
    assertEquals("Laptop", savedProduct.getName());
    assertEquals(category.getId(), savedProduct.getCategory().getId());
  }

  @Test
  void getProductById() {
    Product product = new Product();
    product.setName("Laptop");
    product.setPrice(1000.0);
    product.setDescription("A high-performance laptop");
    product.setStockQuantity(10);
    product.setCategory(category);
    product = productRepository.save(product);

    ProductDTO result = productService.getProductById(product.getId());

    assertNotNull(result);
    assertEquals(product.getId(), result.getId());
    assertEquals("Laptop", result.getName());
    assertEquals(1000.0, result.getPrice());
    assertEquals("A high-performance laptop", result.getDescription());
    assertEquals(10, result.getStockQuantity());
    assertEquals("Electronics", result.getCategoryName());
  }


  @Test
  void updateProduct() {
    Product product = new Product();
    product.setName("Laptop");
    product.setPrice(1000.0);
    product.setDescription("A high-performance laptop");
    product.setStockQuantity(10);
    product.setCategory(category);
    product = productRepository.save(product);

    ProductDTO updatedProductDTO = new ProductDTO();
    updatedProductDTO.setName("Updated Laptop");
    updatedProductDTO.setPrice(1200.0);
    updatedProductDTO.setDescription("An updated high-performance laptop");
    updatedProductDTO.setStockQuantity(5);
    updatedProductDTO.setCategoryName("Electronics");

    ProductDTO result = productService.updateProduct(product.getId(), updatedProductDTO);

    assertNotNull(result);
    assertEquals(product.getId(), result.getId());
    assertEquals("Updated Laptop", result.getName());
    assertEquals(1200.0, result.getPrice());
    assertEquals("An updated high-performance laptop", result.getDescription());
    assertEquals(5, result.getStockQuantity());
    assertEquals("Electronics", result.getCategoryName());

    Product updatedProduct = productRepository.findById(product.getId()).orElse(null);
    assertNotNull(updatedProduct);
    assertEquals("Updated Laptop", updatedProduct.getName());
    assertEquals(1200.0, updatedProduct.getPrice());
  }


  @Test
  void deleteProduct() {
    Product product = new Product();
    product.setName("Laptop");
    product.setPrice(1000.0);
    product.setCategory(category);
    product = productRepository.save(product);

    productService.deleteProduct(product.getId());

    assertFalse(productRepository.findById(product.getId()).isPresent());
  }

  @Test
  void getAllProducts() {
    Product product1 = new Product();
    product1.setName("Laptop");
    product1.setPrice(1000.0);
    product1.setCategory(category);
    productRepository.save(product1);

    Product product2 = new Product();
    product2.setName("Phone");
    product2.setPrice(500.0);
    product2.setCategory(category);
    productRepository.save(product2);

    List<ProductDTO> result = productService.getAllProducts();

    assertNotNull(result);
    assertEquals(2, result.size());
    assertTrue(result.stream().anyMatch(dto -> dto.getName().equals("Laptop")));
    assertTrue(result.stream().anyMatch(dto -> dto.getName().equals("Phone")));
  }
}
