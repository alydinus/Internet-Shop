package kg.spring.project.internet_shop.controller.api;

import java.util.List;
import kg.spring.project.internet_shop.dto.ProductDTO;
import kg.spring.project.internet_shop.dto.payload.request.ProductRequest;
import kg.spring.project.internet_shop.mapper.ProductMapper;
import kg.spring.project.internet_shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductApiController {

  private final ProductService productService;
  private final ProductMapper productMapper;

  @GetMapping
  public ResponseEntity<List<ProductDTO>> getAllProducts() {
    return new ResponseEntity<>(productMapper.toDtoList(productService.getAllProducts()),
        HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
    return new ResponseEntity<>(productMapper.toProductDTO(productService.getProductById(id)),
        HttpStatus.OK);
  }

  @PostMapping("/create")
  public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductRequest productDTO) {
    return new ResponseEntity<>(
        productMapper.toProductDTO(productService.createProduct(productDTO)), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
    System.out.println("ProductDTO = " + productDTO);
    return new ResponseEntity<>(
        productMapper.toProductDTO(productService.updateProduct(id, productDTO)), HttpStatus.OK);
  }

  @PatchMapping("/{id}/price")
  public ResponseEntity<ProductDTO> updateProductPrice(@PathVariable Long id, @RequestParam Double price) {
    return new ResponseEntity<>(
        productMapper.toProductDTO(productService.updateProductPrice(id, price)), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);
    return new ResponseEntity<>("Product deleted successfully", HttpStatus.NO_CONTENT);
  }
}

