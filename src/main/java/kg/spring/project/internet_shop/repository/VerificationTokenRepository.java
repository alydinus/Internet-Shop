package kg.spring.project.internet_shop.repository;

import java.util.Optional;
import kg.spring.project.internet_shop.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

  Optional<VerificationToken> findByToken(String token);

}
