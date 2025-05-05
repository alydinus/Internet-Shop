package kg.spring.project.internet_shop.service.impl;

import java.util.List;
import kg.spring.project.internet_shop.dto.ProductDTO;
import kg.spring.project.internet_shop.dto.payload.request.ProductRequest;
import kg.spring.project.internet_shop.entity.Product;
import kg.spring.project.internet_shop.enums.Category;
import kg.spring.project.internet_shop.exception.exceptions.ProductNotFoundException;
import kg.spring.project.internet_shop.repository.ProductRepository;
import kg.spring.project.internet_shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;


  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public Product getProductById(Long id) {
    return productRepository.findById(id)
        .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));
  }

  public Product createProduct(String name, Double price, String description,
      Integer stockQuantity, String categoryName) {
    Product product = new Product();
    product.setName(name);
    product.setPrice(price);
    product.setDescription(description);
    product.setStockQuantity(stockQuantity);
    Category category = Category.valueOf(categoryName.toUpperCase());
    product.setCategory(category);
    productRepository.save(product);

    return product;

  }

  public Product updateProduct(Long id, String name, Double price, String description,
      Integer stockQuantity, String categoryName) {

    Product product = productRepository.findById(id)
        .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));

    product.setId(id);
    product.setName(name);
    product.setPrice(price);
    product.setDescription(description);
    product.setStockQuantity(stockQuantity);
    product.setCategory(product.getCategory());
    productRepository.save(product);

    return product;
  }

  public Product updateProductPrice(Long id, Double price) {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));

    product.setPrice(price);
    productRepository.save(product);

    return product;
  }

  public void deleteProduct(Long id) {
    productRepository.deleteById(id);
  }
}
