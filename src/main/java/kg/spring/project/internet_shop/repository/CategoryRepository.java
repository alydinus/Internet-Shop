package kg.spring.project.internet_shop.repository;

import java.util.Optional;
import kg.spring.project.internet_shop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

  @Query("SELECT c FROM Category c WHERE c.name = :name")
  Optional<Category> findByName(String name);

}
