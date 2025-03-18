package kg.spring.project.internet_shop.mapper;

import java.util.List;
import java.util.stream.Collectors;
import kg.spring.project.dto.CategoryDTO;
import kg.spring.project.internet_shop.entity.Category;
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


}
