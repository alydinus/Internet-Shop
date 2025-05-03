package kg.spring.project.internet_shop.service;

import java.util.List;
import kg.spring.project.internet_shop.dto.ProductDTO;
import kg.spring.project.internet_shop.dto.payload.request.ProductRequest;
import kg.spring.project.internet_shop.entity.Product;

public interface ProductService {

  List<Product> getAllProducts();

  Product getProductById(Long id);

  Product createProduct(ProductRequest productDTO);

  Product updateProduct(Long id, ProductDTO productDTO);

  Product updateProductPrice(Long id, Double price);

  void deleteProduct(Long id);
}
