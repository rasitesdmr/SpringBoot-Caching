package com.example.springbootcaching.mapper;

import com.example.springbootcaching.dto.UserDTO;
import com.example.springbootcaching.model.User;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO userDTO);

    List<UserDTO> userToUserDTOList(List<User> users);
}
