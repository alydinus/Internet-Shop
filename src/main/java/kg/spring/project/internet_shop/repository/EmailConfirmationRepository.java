package kg.spring.project.internet_shop.repository;

import java.util.Optional;
import kg.spring.project.internet_shop.entity.EmailConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailConfirmationRepository extends JpaRepository<EmailConfirmationToken, Long> {
  Optional<EmailConfirmationToken> findByUserId(Long userId);
}
