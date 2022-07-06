package com.example.springbootcaching.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.ALREADY_REPORTED,reason = "Error : Kullanıcı id' si zaten mevcut !")
public class UserIdAlreadyExistsException extends RuntimeException{
}
