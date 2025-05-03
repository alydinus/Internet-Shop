package kg.spring.project.internet_shop.service;

import java.util.List;
import kg.spring.project.internet_shop.dto.ProductDTO;
import kg.spring.project.internet_shop.entity.Product;

public interface ProductService {

  List<Product> getAllProducts();

  Product getProductById(Long id);

  Product createProduct(ProductDTO productDTO);

  Product updateProduct(Long id, ProductDTO productDTO);

  Product updateProductPrice(Long id, ProductDTO dto);

  void deleteProduct(Long id);
}
