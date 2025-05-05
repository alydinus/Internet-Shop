package kg.spring.project.internet_shop.security;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Collections;
import java.util.Date;
import kg.spring.project.internet_shop.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext context;

  @Mock
  private UserService userService;

  @Value("${jwt.secret}")
  private String jwtSecret;

  @BeforeEach
  void setup() {
    mockMvc = MockMvcBuilders
        .webAppContextSetup(context)
        .apply(springSecurity())
        .build();
  }

  private String generateToken(String username, String role) {
    return Jwts.builder()
        .setSubject(username)
        .claim("roles", Collections.singletonList(role))
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
        .signWith(SignatureAlgorithm.HS256, jwtSecret.getBytes())
        .compact();
  }


  @Test
  void getAllProducts_Unauthenticated_Returns200() throws Exception {
    mockMvc.perform(get("/api/products"))
        .andExpect(status().isOk());
  }


  @Test
  void createProduct_WithAdminRole_Returns201() throws Exception {
    String adminToken = generateToken("admin", "ROLE_ADMIN");

    System.out.println("Admin token: " + adminToken);

    mockMvc.perform(post("/api/products/create")
            .header("Authorization", "Bearer " + adminToken)
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {
                    "name": "Test Product",
                    "price": 100.0,
                    "description": "Test Description",
                    "stockQuantity": 10,
                    "categoryName": "PHONE"
                }
                """))
        .andExpect(status().isCreated());
  }

  @Test
  void deleteProduct_WithUserRole_Returns403() throws Exception {
    String userToken = generateToken("user", "ROLE_USER");

    mockMvc.perform(delete("/api/products/1")
            .header("Authorization", "Bearer " + userToken))
        .andExpect(status().isForbidden());
  }
}