package kg.spring.project.internet_shop.repository;

import kg.spring.project.internet_shop.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
