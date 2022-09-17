package com.Practice.Blog.Payloads;

import com.Practice.Blog.Models.Category;
import com.Practice.Blog.Models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class PostDto {

    private  int postId;
    private String title;
    private String content ;
    private String imageName;
    private Date addedDate;

    private CategoryDTO category;
    private UserDto user;
}
