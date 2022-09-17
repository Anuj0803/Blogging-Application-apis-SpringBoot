package com.Practice.Blog.Controllers;

import com.Practice.Blog.Payloads.UserDto;
import com.Practice.Blog.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<UserDto> add(@Valid @RequestBody UserDto userDto){
       UserDto createdUserDto =userService.createUser(userDto);
       return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> update(@Valid @RequestBody UserDto userDto , @PathVariable("userId") int id){
        UserDto UpdatedUser =userService.updateUser(userDto , id);
        return new ResponseEntity<>(UpdatedUser, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> get(@PathVariable("id") int id){
        UserDto userDto= userService.getUserById(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public List<UserDto> getAll(){
        List<UserDto> userDtos = userService.getAllUsers();
        return  userDtos ;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") int id){
        userService.deleteUser(id);
    }
}
