package kg.spring.project.internet_shop.mapper;

import java.util.List;
import kg.spring.project.internet_shop.dto.ProductDTO;
import kg.spring.project.internet_shop.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  ProductDTO toProductDTO(Product product);

  Product toProduct(ProductDTO productDTO);

  List<ProductDTO> toDtoList(List<Product> products);

  List<Product> toEntityList(List<ProductDTO> productDTOs);

}
