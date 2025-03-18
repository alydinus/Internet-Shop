package kg.spring.project.internet_shop.repository;

import kg.spring.project.internet_shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
