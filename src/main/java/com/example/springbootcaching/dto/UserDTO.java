package com.example.springbootcaching.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    private String email;
}
