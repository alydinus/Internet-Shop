package kg.spring.project.mapper;

import java.util.List;
import java.util.stream.Collectors;
import kg.spring.project.dto.CategoryDTO;
import kg.spring.project.dto.ProductDTO;
import kg.spring.project.internet_shop.entity.Category;
import kg.spring.project.internet_shop.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

  public CategoryDTO toCategoryDTO(Category category) {
    if (category == null) {
      return null;
    }

    CategoryDTO dto = new CategoryDTO();
    dto.setId(category.getId());
    dto.setName(category.getName());

    return dto;
  }

  public Category toCategory(CategoryDTO categoryDTO) {
    if (categoryDTO == null) {
      return null;
    }

    Category category = new Category();
    category.setId(categoryDTO.getId());
    category.setName(categoryDTO.getName());

    return category;
  }

  public List<CategoryDTO> toDtoList(List<Category> categories) {
    if (categories == null) {
      return null;
    }
    return categories.stream()
        .map(this::toCategoryDTO)
        .collect(Collectors.toList());
  }

  public List<Category> toEntityList(List<CategoryDTO> categoryDTOs) {
    if (categoryDTOs == null) {
      return null;
    }
    return categoryDTOs.stream()
        .map(this::toCategory)
        .collect(Collectors.toList());
  }

  private ProductDTO toProductDTO(Product product) {
    if (product == null) {
      return null;
    }

    ProductDTO dto = new ProductDTO();
    dto.setId(product.getId());
    dto.setName(product.getName());
    dto.setPrice(product.getPrice());
    dto.setDescription(product.getDescription());

    return dto;
  }

  private Product toProduct(ProductDTO productDTO) {
    if (productDTO == null) {
      return null;
    }

    Product product = new Product();
    product.setId(productDTO.getId());
    product.setName(productDTO.getName());
    product.setPrice(productDTO.getPrice());
    product.setDescription(productDTO.getDescription());

    return product;
  }
}
