package kg.spring.project.internet_shop.controller.api.controllers;

import java.util.List;
import kg.spring.project.internet_shop.controller.api.ProductApi;
import kg.spring.project.internet_shop.dto.ProductDTO;
import kg.spring.project.internet_shop.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductApiController implements ProductApi {

  private final ProductService productService;

  public ProductApiController(ProductService productService) {
    this.productService = productService;
  }

  public ResponseEntity<List<ProductDTO>> getAllProducts() {
    return ResponseEntity.ok(productService.getAllProducts());
  }

  public ResponseEntity<ProductDTO> getProductById(Long id) {
    return ResponseEntity.ok(productService.getProductById(id));
  }

  public ResponseEntity<ProductDTO> createProduct(ProductDTO dto) {
    return ResponseEntity.ok(productService.createProduct(dto));
  }

  public ResponseEntity<ProductDTO> updateProduct(Long id, ProductDTO dto) {
    return ResponseEntity.ok(productService.updateProduct(id, dto));
  }

  public ResponseEntity<Void> deleteProduct(Long id) {
    productService.deleteProduct(id);
    return ResponseEntity.noContent().build();
  }
}

