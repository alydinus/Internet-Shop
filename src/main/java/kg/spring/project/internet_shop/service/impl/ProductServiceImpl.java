package kg.spring.project.internet_shop.service.impl;

import java.util.List;
import kg.spring.project.internet_shop.dto.ProductDTO;
import kg.spring.project.internet_shop.dto.payload.request.ProductRequest;
import kg.spring.project.internet_shop.entity.Product;
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

  public Product createProduct(ProductRequest productRequest) {
    Product product = new Product();
    product.setName(productRequest.getName());
    product.setPrice(productRequest.getPrice());
    product.setDescription(productRequest.getDescription());
    product.setStockQuantity(productRequest.getStockQuantity());
    product.setCategory(product.getCategory());
    productRepository.save(product);

    return product;

  }

  public Product updateProduct(Long id, ProductDTO productDTO) {

    Product product = productRepository.findById(id)
        .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));

    product.setName(productDTO.getName());
    product.setPrice(productDTO.getPrice());
    product.setDescription(productDTO.getDescription());
    product.setStockQuantity(productDTO.getStockQuantity());
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
