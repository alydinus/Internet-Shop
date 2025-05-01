package kg.spring.project.internet_shop.mapper;

import java.util.List;
import kg.spring.project.internet_shop.dto.CategoryDTO;
import kg.spring.project.internet_shop.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

  CategoryDTO toCategoryDTO(Category category);

  Category toCategory(CategoryDTO categoryDTO);

  List<CategoryDTO> toDtoList(List<Category> categories);

  List<Category> toEntityList(List<CategoryDTO> categoryDTOs);

}
