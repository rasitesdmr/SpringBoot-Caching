package com.example.springbootcaching.service;

import com.example.springbootcaching.dto.UserDTO;
import com.example.springbootcaching.exception.*;
import com.example.springbootcaching.mapper.UserMapper;
import com.example.springbootcaching.model.User;
import com.example.springbootcaching.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getByUserId(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User createUser(UserDTO userDTO) {
        if (userRepository.getByFirstName(userDTO.getFirstName()).isPresent()) {
            throw new FirstNameAlreadyExistsException();
        }
        if (userRepository.getByLastName(userDTO.getLastName()).isPresent()) {
            throw new LastNameAlreadyExistsException();
        }
        if (userRepository.getByEmail(userDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException();
        }
        User userMap = userMapper.userDTOToUser(userDTO);
        return userRepository.save(userMap);
    }

    @Override
    public User updateUser(Long id, UserDTO userDTO) {

        User newUser = userRepository.findById(id).orElseThrow(UserIdAlreadyExistsException::new);
        newUser.setFirstName(userDTO.getFirstName());
        newUser.setLastName(userDTO.getLastName());
        newUser.setEmail(userDTO.getEmail());

        Optional<User> optionalUser = userRepository.getByFirstName(userDTO.getFirstName());
        if (!Objects.equals(newUser.getFirstName(), userDTO.getFirstName()) && optionalUser.isPresent()) {
            throw new FirstNameAlreadyExistsException();
        }
        optionalUser = userRepository.getByLastName(userDTO.getLastName());
        if (!Objects.equals(newUser.getLastName(), userDTO.getLastName()) && optionalUser.isPresent()) {
            throw new LastNameAlreadyExistsException();
        }
        optionalUser = userRepository.getByEmail(userDTO.getEmail());
        if (!Objects.equals(newUser.getEmail(), userDTO.getEmail()) && optionalUser.isPresent()) {
            throw new EmailAlreadyExistsException();
        }
        return userRepository.save(newUser);

    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotNullException::new);
        userRepository.delete(user);

    }


}
