package kg.spring.project.internet_shop.repository;

import java.util.List;
import kg.spring.project.internet_shop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
  @Query("SELECT o FROM Order o JOIN FETCH o.orderItems WHERE o.user.id = ?1")
  List<Order> findAllByUserId(Long userId);

}
