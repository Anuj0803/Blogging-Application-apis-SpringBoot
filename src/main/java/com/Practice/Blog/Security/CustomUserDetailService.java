package com.Practice.Blog.Security;

import com.Practice.Blog.Exceptions.ResourceNotFoundException;
import com.Practice.Blog.Models.User;
import com.Practice.Blog.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  repo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User" ,"Email" +username ,0));
        return user;
    }
}
