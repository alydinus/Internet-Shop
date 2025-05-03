package kg.spring.project.internet_shop.mapper;

import java.util.List;
import kg.spring.project.internet_shop.dto.UserDTO;
import kg.spring.project.internet_shop.entity.User;

public interface UserMapper {

  List<UserDTO> toUserDTOList(List<User> user);

  UserDTO toUserDTO(User user);
}
