package kg.spring.project.internet_shop.repository;

import kg.spring.project.internet_shop.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
