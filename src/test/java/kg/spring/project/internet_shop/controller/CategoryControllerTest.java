package kg.spring.project.internet_shop.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jakarta.transaction.Transactional;
import kg.spring.project.internet_shop.entity.Category;
import kg.spring.project.internet_shop.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class CategoryControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private CategoryRepository categoryRepository;

  @BeforeEach
  void setUp() {
    categoryRepository.deleteAll();
    Category category1 = new Category();
    category1.setName("Phones");

    categoryRepository.save(category1);
  }

  @Test
  void testGetCategoryByName() throws Exception {
    mockMvc.perform(get("/api/category/name/Phones"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Phones"));
  }

  @Test
  void testFullCategoryWorkflow() throws Exception {
    mockMvc.perform(post("/api/category")
        .contentType("application/json")
        .content("{\"name\": \"Computers\"}"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Computers"));

    mockMvc.perform(get("/api/category/name/Computers"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Computers"));

    Category category = categoryRepository.findByName("Computers").orElseThrow();
    mockMvc.perform(delete("/api/category/" + category.getId()))
        .andExpect(status().isOk());

    mockMvc.perform(get("/api/category/name/Computers"))
        .andExpect(status().isNotFound());

  }
}
