package com.example.springbootcaching.service;

import com.example.springbootcaching.dto.UserDTO;
import com.example.springbootcaching.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User getByUserId(Long id);

    List<User> getUsers();

    User createUser(UserDTO userDTO);

    User updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long id);


}
