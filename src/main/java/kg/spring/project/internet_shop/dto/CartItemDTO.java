package kg.spring.project.internet_shop.dto;

import lombok.Data;

@Data
public class CartItemDTO {
  private String productName;
  private int quantity;
  private Double price;
}
