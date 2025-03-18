package kg.spring.project.internet_shop.mapper;

import java.util.List;
import java.util.stream.Collectors;
import kg.spring.project.dto.ProductDTO;
import kg.spring.project.internet_shop.entity.Product;
import kg.spring.project.internet_shop.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

  private final CategoryMapper categoryMapper;
  private final CategoryService categoryService;

  public ProductMapper(CategoryMapper categoryMapper, CategoryService categoryService) {
    this.categoryMapper = categoryMapper;
    this.categoryService = categoryService;
  }

  public ProductDTO toProductDTO(Product product) {
    if (product == null) {
      return null;
    }

    ProductDTO dto = new ProductDTO();
    dto.setId(product.getId());
    dto.setName(product.getName());
    dto.setPrice(product.getPrice());
    dto.setCategoryName(categoryMapper.toCategoryDTO(product.getCategory()).getName());
    dto.setDescription(product.getDescription());
    dto.setStockQuantity(product.getStockQuantity());

    return dto;
  }

  public Product toProduct(ProductDTO productDTO) {
    if (productDTO == null) {
      return null;
    }

    Product product = new Product();
    product.setId(productDTO.getId());
    product.setName(productDTO.getName());
    product.setPrice(productDTO.getPrice());
    product.setCategory(categoryMapper.toCategory(categoryService.getCategoryByName(productDTO.getCategoryName())));
    product.setDescription(productDTO.getDescription());
    product.setStockQuantity(productDTO.getStockQuantity());

    return product;
  }

  public List<ProductDTO> toDtoList(List<Product> products) {
    if (products == null) {
      return null;
    }
    return products.stream()
        .map(this::toProductDTO)
        .collect(Collectors.toList());
  }

  public List<Product> toEntityList(List<ProductDTO> productDTOs) {
    if (productDTOs == null) {
      return null;
    }
    return productDTOs.stream()
        .map(this::toProduct)
        .collect(Collectors.toList());
  }
}
