package kg.spring.project.internet_shop.repository;

import kg.spring.project.internet_shop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
