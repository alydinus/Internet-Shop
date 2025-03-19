package kg.spring.project.internet_shop.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import kg.spring.project.internet_shop.controller.api.controllers.CategoryApiController;
import kg.spring.project.internet_shop.dto.CategoryDTO;
import kg.spring.project.internet_shop.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class CategoryApiControllerTest {

  @Mock
  private CategoryService categoryService;

  @InjectMocks
  private CategoryApiController categoryApiController;

  private MockMvc mockMvc;
  private ObjectMapper objectMapper;
  private CategoryDTO categoryDTO;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(categoryApiController).build();
    objectMapper = new ObjectMapper();

    categoryDTO = new CategoryDTO();
    categoryDTO.setId(1L);
    categoryDTO.setName("Electronics");
  }

  @Test
  void getAllCategories() throws Exception {
    List<CategoryDTO> categories = Arrays.asList(categoryDTO);
    when(categoryService.getAllCategories()).thenReturn(categories);

    mockMvc.perform(get("/api/category"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].id", is(1)))
        .andExpect(jsonPath("$[0].name", is("Electronics")));

    verify(categoryService, times(1)).getAllCategories();
  }

  @Test
  void getCategoryById() throws Exception {
    when(categoryService.getCategoryById(1L)).thenReturn(categoryDTO);

    mockMvc.perform(get("/api/category/{id}", 1L))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.name", is("Electronics")));

    verify(categoryService, times(1)).getCategoryById(1L);
  }

  @Test
  void getCategoryByName() throws Exception {
    when(categoryService.getCategoryByName("Electronics")).thenReturn(categoryDTO);

    mockMvc.perform(get("/api/category/name/{name}", "Electronics"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.name", is("Electronics")));

    verify(categoryService, times(1)).getCategoryByName("Electronics");
  }

  @Test
  void createCategory() throws Exception {
    when(categoryService.createCategory(any(CategoryDTO.class))).thenReturn(categoryDTO);

    mockMvc.perform(post("/api/category")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(categoryDTO)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.name", is("Electronics")));

    verify(categoryService, times(1)).createCategory(any(CategoryDTO.class));
  }

  @Test
  void updateCategory() throws Exception {
    when(categoryService.updateCategory(eq(1L), any(CategoryDTO.class))).thenReturn(categoryDTO);

    mockMvc.perform(put("/api/category/{id}", 1L)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(categoryDTO)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.name", is("Electronics")));

    verify(categoryService, times(1)).updateCategory(eq(1L), any(CategoryDTO.class));
  }

  @Test
  void deleteCategory() throws Exception {
    doNothing().when(categoryService).deleteCategory(1L);

    mockMvc.perform(delete("/api/category/{id}", 1L))
        .andExpect(status().isOk())
        .andExpect(content().string("Category deleted"));

    verify(categoryService, times(1)).deleteCategory(1L);
  }
}