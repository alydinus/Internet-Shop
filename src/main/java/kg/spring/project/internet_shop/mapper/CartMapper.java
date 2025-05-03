package kg.spring.project.internet_shop.mapper;

import kg.spring.project.internet_shop.dto.payload.response.CartResponse;
import kg.spring.project.internet_shop.entity.Cart;
import kg.spring.project.internet_shop.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {

  @Mapping(source = "userId", target = "user")
  Cart toEntity(CartResponse cartResponse);

  @Mapping(source = "user.id", target = "userId")
  CartResponse toDTO(Cart cart);

  default User mapUserIdToUser(Long userId) {
    if (userId == null) {
      return null;
    }
    User user = new User();
    user.setId(userId);
    return user;
  }
}
