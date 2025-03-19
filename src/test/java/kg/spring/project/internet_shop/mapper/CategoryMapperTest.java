package kg.spring.project.internet_shop.mapper;

import kg.spring.project.internet_shop.dto.CategoryDTO;
import kg.spring.project.internet_shop.entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

  private CategoryMapper categoryMapper;

  private Category category;
  private CategoryDTO categoryDTO;

  @BeforeEach
  void setUp() {
    categoryMapper = new CategoryMapper();

    category = new Category();
    category.setId(1L);
    category.setName("Electronics");

    categoryDTO = new CategoryDTO();
    categoryDTO.setId(1L);
    categoryDTO.setName("Electronics");
  }

  @Test
  void toCategoryDTO() {
    CategoryDTO result = categoryMapper.toCategoryDTO(category);

    assertNotNull(result);
    assertEquals(category.getId(), result.getId());
    assertEquals(category.getName(), result.getName());
  }

  @Test
  void toCategory() {
    Category result = categoryMapper.toCategory(categoryDTO);

    assertNotNull(result);
    assertEquals(categoryDTO.getId(), result.getId());
    assertEquals(categoryDTO.getName(), result.getName());
  }


  @Test
  void toDtoList() {
    Category category2 = new Category();
    category2.setId(2L);
    category2.setName("Books");

    List<Category> categories = Arrays.asList(category, category2);

    List<CategoryDTO> result = categoryMapper.toDtoList(categories);

    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals(category.getId(), result.get(0).getId());
    assertEquals(category.getName(), result.get(0).getName());
    assertEquals(category2.getId(), result.get(1).getId());
    assertEquals(category2.getName(), result.get(1).getName());
  }

  @Test
  void toEntityList() {
    CategoryDTO categoryDTO2 = new CategoryDTO();
    categoryDTO2.setId(2L);
    categoryDTO2.setName("Books");

    List<CategoryDTO> categoryDTOs = Arrays.asList(categoryDTO, categoryDTO2);

    List<Category> result = categoryMapper.toEntityList(categoryDTOs);

    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals(categoryDTO.getId(), result.get(0).getId());
    assertEquals(categoryDTO.getName(), result.get(0).getName());
    assertEquals(categoryDTO2.getId(), result.get(1).getId());
    assertEquals(categoryDTO2.getName(), result.get(1).getName());
  }

}