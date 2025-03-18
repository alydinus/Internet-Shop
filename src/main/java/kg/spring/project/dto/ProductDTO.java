package kg.spring.project.dto;

public class ProductDTO {

  private Long id;
  private String name;
  private String description;
  private Double price;
  private String categoryName;
  private int stockQuantity;

  public ProductDTO(Long id, String name, String description, Double price,
      String categoryName, int stockQuantity) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.categoryName = categoryName;
    this.stockQuantity = stockQuantity;
  }

  public ProductDTO() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public int getStockQuantity() {
    return stockQuantity;
  }

  public void setStockQuantity(int stockQuantity) {
    this.stockQuantity = stockQuantity;
  }
}
