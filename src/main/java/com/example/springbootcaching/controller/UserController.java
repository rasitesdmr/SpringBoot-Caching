package com.example.springbootcaching.controller;

import com.example.springbootcaching.dto.UserDTO;
import com.example.springbootcaching.mapper.UserMapper;
import com.example.springbootcaching.model.User;
import com.example.springbootcaching.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @GetMapping("/list")
    @Cacheable("cacheUsers")
    public ResponseEntity<List<UserDTO>> findAllUsers() {
        System.out.println("Veri tabanından çekildi");
        List<UserDTO> userDTOList = userMapper.userToUserDTOList(userService.getUsers());
        return ResponseEntity.ok(userDTOList);
    }

    @GetMapping("/list/{id}")
    @Cacheable(value = "cacheUsers" ,key = "#id")
    public ResponseEntity<User> getByUserId(@PathVariable Long id) {
        User userById = userService.getByUserId(id);
        return new ResponseEntity<>(userById, HttpStatus.CREATED);
    }

    @PostMapping("/save")
    @Caching(put = @CachePut(value = "cacheUsers" , key = "#result.id") , evict = @CacheEvict(value = "cacheUsers", allEntries = true))
    public User createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }

    @DeleteMapping("/delete/{id}")
    @CacheEvict(value = "cacheUsers",key = "#id")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

}
