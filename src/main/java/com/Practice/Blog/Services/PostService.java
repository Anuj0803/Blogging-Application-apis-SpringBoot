package com.Practice.Blog.Services;

import com.Practice.Blog.Payloads.PostDto;

import java.util.List;

public interface PostService {

      PostDto createPost(PostDto postDto , int UserId , int CategoryId);
      PostDto updatePost(PostDto postDto , int id);
      void  deletePost(int id);
      PostDto getPost(int id);
      List<PostDto> post();

      //Get all posts by Category
    List<PostDto> getAllPostByCategory(int category_id);

    //Get all posts by User
    List<PostDto> getAllPostsByUser(int user_id);

    List<PostDto> searchPost(String keyword);
}
