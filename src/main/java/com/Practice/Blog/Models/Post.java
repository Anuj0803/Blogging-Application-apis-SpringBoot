package com.Practice.Blog.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int postId;
    private String title;
    private String content ;
    private String imageName;
    private Date addedDate;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;
}
