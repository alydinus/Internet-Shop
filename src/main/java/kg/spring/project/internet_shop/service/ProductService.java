package kg.spring.project.internet_shop.service;

import java.util.List;
import kg.spring.project.dto.ProductDTO;

public interface ProductService {

  List<ProductDTO> getAllProducts();

  ProductDTO getProductById(Long id);

  ProductDTO createProduct(ProductDTO productDTO);

  ProductDTO updateProduct(Long id, ProductDTO productDTO);

  ProductDTO updateProductPrice(Long id, ProductDTO dto);

  void deleteProduct(Long id);
}
