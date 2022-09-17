package com.Practice.Blog.Services.impl;

import com.Practice.Blog.Exceptions.ResourceNotFoundException;
import com.Practice.Blog.Models.User;
import com.Practice.Blog.Payloads.UserDto;
import com.Practice.Blog.Repository.UserRepo;
import com.Practice.Blog.Services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import static com.Practice.Blog.Config.AppConstants.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo repo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user1 = this.dtoToUser(userDto);
        User savedUser = repo.save(user1);
        return  this.UserTodto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, int id) {
        User user1 = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException(USER ,ID ,id));
        user1.setName(userDto.getName());
        user1.setEmail(userDto.getEmail());
        user1.setPassword(userDto.getPassword());
        user1.setAbout(userDto.getAbout());
        User updatedUser = repo.save(user1);
        UserDto userDto1 = this.UserTodto(updatedUser);
        return userDto1;
    }
    @Override
    public UserDto getUserById(int id) {
        User user1 = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException(USER ,ID ,id));
        UserDto userDto = this.UserTodto(user1);
        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = repo.findAll();

       List<UserDto> userDtos = users.stream().map(this::UserTodto).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(int id) {
         User user = repo.findById(id).orElseThrow(() ->new ResourceNotFoundException(USER ,ID ,id));
         repo.delete(user);
    }

    public User dtoToUser(UserDto userDto){
        User user = modelMapper.map(userDto,User.class);
        /*user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());*/
        return user;
    }

    public UserDto UserTodto(User user){
        UserDto user1 = modelMapper.map(user,UserDto.class);
        /*user1.setId(user.getId());
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setAbout(user.getAbout());*/
        return user1;
    }
}
