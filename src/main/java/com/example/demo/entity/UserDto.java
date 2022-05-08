package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class UserDto {

    @NotBlank(message = "Login cannot be blank")
    @Pattern(regexp = "^[A-Za-z0-9]{4,30}$", message = "Login needs to include only alphanumeric characters (4-30 characters)")
    private String login;


    @NotBlank(message = "Password cannot be blank")
    @Pattern(regexp = "^\\s*(?:\\S\\s*){8,20}$", message = "Password should be between 8 and 20 characters")
    private String password;

}
