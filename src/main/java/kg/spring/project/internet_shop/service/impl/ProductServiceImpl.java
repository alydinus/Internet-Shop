package kg.spring.project.internet_shop.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import kg.spring.project.internet_shop.dto.ProductDTO;
import kg.spring.project.internet_shop.entity.Category;
import kg.spring.project.internet_shop.entity.Product;
import kg.spring.project.internet_shop.mapper.CategoryMapper;
import kg.spring.project.internet_shop.repository.ProductRepository;
import kg.spring.project.internet_shop.service.CategoryService;
import kg.spring.project.internet_shop.service.ProductService;
import kg.spring.project.internet_shop.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;


  public List<ProductDTO> getAllProducts() {
    return productRepository.findAll().stream()
        .map(productMapper::toProductDTO)
        .collect(Collectors.toList());
  }

  public ProductDTO getProductById(Long id) {
    return productMapper.toProductDTO(productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Product with id " + id + " not found")));
  }

  public ProductDTO createProduct(ProductDTO productDTO) {
    Product product = productMapper.toProduct(productDTO);
    productRepository.save(product);
    return productMapper.toProductDTO(product);

  }

  public ProductDTO updateProduct(Long id, ProductDTO productDTO) {

    Product product = productRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id " + id + " not found"));

    product.setName(productDTO.getName());
    product.setPrice(productDTO.getPrice());
    product.setDescription(productDTO.getDescription());
    product.setStockQuantity(productDTO.getStockQuantity());

    return productMapper.toProductDTO(productRepository.save(product));
  }

  public ProductDTO updateProductPrice(Long id, ProductDTO dto) {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id " + id + " not found"));

    product.setPrice(dto.getPrice());

    return productMapper.toProductDTO(productRepository.save(product));
  }

  public void deleteProduct(Long id) {
    productRepository.deleteById(id);
  }
}
