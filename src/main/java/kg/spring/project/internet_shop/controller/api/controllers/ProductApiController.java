package kg.spring.project.internet_shop.controller.api.controllers;

import java.util.List;
import kg.spring.project.internet_shop.controller.api.ProductApi;
import kg.spring.project.internet_shop.dto.ProductDTO;
import kg.spring.project.internet_shop.dto.payload.request.ProductRequest;
import kg.spring.project.internet_shop.mapper.ProductMapper;
import kg.spring.project.internet_shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductApiController implements ProductApi {

  private final ProductService productService;
  private final ProductMapper productMapper;

  public ResponseEntity<List<ProductDTO>> getAllProducts() {
    return new ResponseEntity<>(productMapper.toDtoList(productService.getAllProducts()),
        HttpStatus.OK);
  }

  public ResponseEntity<ProductDTO> getProductById(Long id) {
    return new ResponseEntity<>(productMapper.toProductDTO(productService.getProductById(id)),
        HttpStatus.OK);
  }

  public ResponseEntity<ProductDTO> createProduct(ProductRequest productDTO) {
    return new ResponseEntity<>(
        productMapper.toProductDTO(productService.createProduct(productDTO)), HttpStatus.CREATED);
  }

  public ResponseEntity<ProductDTO> updateProduct(Long id, ProductDTO productDTO) {
    return new ResponseEntity<>(
        productMapper.toProductDTO(productService.updateProduct(id, productDTO)), HttpStatus.OK);
  }

  public ResponseEntity<String> deleteProduct(Long id) {
    productService.deleteProduct(id);
    return new ResponseEntity<>("Product deleted successfully", HttpStatus.NO_CONTENT);
  }
}

