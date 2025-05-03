package kg.spring.project.internet_shop.mapper;

import java.util.List;
import kg.spring.project.internet_shop.dto.ProductDTO;
import kg.spring.project.internet_shop.entity.Product;
import org.mapstruct.Mapper;

public interface ProductMapper {

  ProductDTO toProductDTO(Product product);

  List<ProductDTO> toDtoList(List<Product> products);

}
