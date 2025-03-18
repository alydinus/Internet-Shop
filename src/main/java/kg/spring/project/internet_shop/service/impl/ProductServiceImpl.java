package kg.spring.project.internet_shop.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import kg.spring.project.dto.ProductDTO;
import kg.spring.project.internet_shop.entity.Category;
import kg.spring.project.internet_shop.entity.Product;
import kg.spring.project.internet_shop.mapper.CategoryMapper;
import kg.spring.project.internet_shop.repository.CategoryRepository;
import kg.spring.project.internet_shop.repository.ProductRepository;
import kg.spring.project.internet_shop.service.CategoryService;
import kg.spring.project.internet_shop.service.ProductService;
import kg.spring.project.internet_shop.mapper.ProductMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;
  private final CategoryMapper categoryMapper;
  private final CategoryService categoryService;


  public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper,
      CategoryMapper categoryMapper,
      CategoryService categoryService) {
    this.productRepository = productRepository;
    this.productMapper = productMapper;
    this.categoryMapper = categoryMapper;
    this.categoryService = categoryService;
  }

  public List<ProductDTO> getAllProducts() {
    return productRepository.findAll().stream()
        .map(productMapper::toProductDTO)
        .collect(Collectors.toList());
  }

  public ProductDTO getProductById(Long id) {
    return productMapper.toProductDTO(productRepository.findById(id).orElse(null));
  }

  public ProductDTO createProduct(ProductDTO productDTO) {
    Product product = productMapper.toProduct(productDTO);
    Category category = categoryMapper.toCategory(
        categoryService.getCategoryByName(productDTO.getCategoryName()));
    product.setCategory(category);
    productRepository.save(product);
    return productMapper.toProductDTO(product);

  }

  public ProductDTO updateProduct(Long id, ProductDTO productDTO) {

    Product product = productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Product not found"));

    product.setName(productDTO.getName());
    product.setPrice(productDTO.getPrice());
    product.setCategory(
        categoryMapper.toCategory(categoryService.getCategoryByName(productDTO.getCategoryName())));
    product.setDescription(productDTO.getDescription());
    product.setStockQuantity(productDTO.getStockQuantity());

    return productMapper.toProductDTO(productRepository.save(product));
  }

  public ProductDTO updateProductPrice(Long id, ProductDTO dto) {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Product not found"));

    product.setPrice(dto.getPrice());

    return productMapper.toProductDTO(productRepository.save(product));
  }

  public void deleteProduct(Long id) {
    productRepository.deleteById(id);
  }
}
