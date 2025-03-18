package kg.spring.project.internet_shop.dto;

public class OrderItemDTO {
  private Long productId;
  private int quantity;
  private double price;

  public OrderItemDTO() {
  }

  public OrderItemDTO(Long productId, int quantity, double price) {
    this.productId = productId;
    this.quantity = quantity;
    this.price = price;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }
}
