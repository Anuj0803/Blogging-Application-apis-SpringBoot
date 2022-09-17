package com.Practice.Blog.Services;

import com.Practice.Blog.Payloads.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user , int id);
    UserDto getUserById(int id);
    List<UserDto> getAllUsers();
    void deleteUser(int id);
}
