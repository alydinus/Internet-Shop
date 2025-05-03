package kg.spring.project.internet_shop.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import kg.spring.project.internet_shop.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

  private Long id;
  private String name;
  private String description;
  private Double price;
  private Category categoryName;
  private int stockQuantity;

}
