package kg.spring.project.internet_shop.mapper.impl;

import java.util.ArrayList;
import java.util.List;
import kg.spring.project.internet_shop.dto.ProductDTO;
import kg.spring.project.internet_shop.entity.Product;
import kg.spring.project.internet_shop.mapper.ProductMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapperImpl implements ProductMapper {

  public ProductDTO toProductDTO(Product product) {
    ProductDTO productDTO = new ProductDTO();
    productDTO.setId(product.getId());
    productDTO.setName(product.getName());
    productDTO.setPrice(product.getPrice());
    productDTO.setDescription(product.getDescription());
    productDTO.setStockQuantity(product.getStockQuantity());
    productDTO.setCategoryName(product.getCategory().toString());
    return productDTO;
  }

  public List<ProductDTO> toDtoList(List<Product> products) {
    List<ProductDTO> productDTOs = new ArrayList<>();
    for (Product product : products) {
      productDTOs.add(toProductDTO(product));
    }
    return productDTOs;
  }

}
