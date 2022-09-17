package com.Practice.Blog.Payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {

    private int id;

    @NotEmpty
    @Size(min = 4 , message = "Name should be more than 3 words")
    private String name;

    @Email(message = "Please enter valid email")
    private  String email;

    @NotEmpty
    @Size(min=3 ,max = 10 ,message = "Password should be between 3 to 10 chars")
    private  String password;

    private String about;
}
