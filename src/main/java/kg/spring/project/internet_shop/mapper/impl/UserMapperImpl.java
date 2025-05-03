package kg.spring.project.internet_shop.mapper.impl;

import java.util.ArrayList;
import java.util.List;
import kg.spring.project.internet_shop.dto.UserDTO;
import kg.spring.project.internet_shop.entity.User;
import kg.spring.project.internet_shop.mapper.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

  public List<UserDTO> toUserDTOList(List<User> user) {
    List<UserDTO> userDTOList = new ArrayList<>();
    for (User u : user) {
      toUserDTO(u);
      userDTOList.add(toUserDTO(u));
    }
    return userDTOList;
  }

  public UserDTO toUserDTO(User user) {
    UserDTO userDTO = new UserDTO();
    userDTO.setId(user.getId());
    userDTO.setEmail(user.getEmail());
    userDTO.setFirstName(user.getFirstName());
    userDTO.setLastName(user.getLastName());
    userDTO.setRole(user.getRole());
    userDTO.setUsername(user.getUsername());
    userDTO.setPassword(user.getPassword());
    return userDTO;
  }
}
