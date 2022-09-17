package com.Practice.Blog.Models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private  String name ;

    @ManyToMany
    private List<User> users ;
}
