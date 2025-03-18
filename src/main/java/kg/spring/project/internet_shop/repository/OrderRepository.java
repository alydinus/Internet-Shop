package kg.spring.project.internet_shop.repository;

import kg.spring.project.internet_shop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
