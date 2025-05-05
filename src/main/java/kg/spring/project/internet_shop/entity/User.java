package kg.spring.project.internet_shop.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import kg.spring.project.internet_shop.enums.Role;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email")
  private String email;

  @Column(name = "registration_date")
  private LocalDateTime registrationDate;

  @Column(name = "role")
  @Enumerated(EnumType.STRING)
  private Role role;

  @Column(name = "refresh_token")
  private String refreshToken;

  @Column(name = "is_active")
  private boolean isActive;

  @Column(name = "2fa")
  private boolean twoFA;

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
  private Cart cart;

  @OneToMany(mappedBy = "user")
  private List<Order> orders;


}
