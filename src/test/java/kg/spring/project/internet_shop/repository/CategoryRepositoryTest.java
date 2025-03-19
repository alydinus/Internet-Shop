package kg.spring.project.internet_shop.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import jakarta.transaction.Transactional;
import java.util.Optional;
import kg.spring.project.internet_shop.entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
public class CategoryRepositoryTest {

  @Autowired
  private CategoryRepository categoryRepository;


  @BeforeEach
  void setUp() {
    Category category1 = new Category();
    category1.setName("Computers");

    Category category2 = new Category();
    category2.setName("Smartphones");

    categoryRepository.save(category1);
    categoryRepository.save(category2);

  }

  @Test
  void findByName() {
    Optional<Category> categoryOpt = categoryRepository.findByName("Computers");
    assertTrue(categoryOpt.isPresent(), "Category 'Computers' should be found");
    assertEquals("Computers", categoryOpt.get().getName());

    categoryOpt = categoryRepository.findByName("Smartphones");
    assertTrue(categoryOpt.isPresent(), "Category 'Smartphones' should be found");
    assertEquals("Smartphones", categoryOpt.get().getName());
  }

}
