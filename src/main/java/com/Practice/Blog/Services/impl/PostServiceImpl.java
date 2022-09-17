package com.Practice.Blog.Services.impl;

import com.Practice.Blog.Exceptions.CategoryNotFoundException;
import com.Practice.Blog.Exceptions.PostNotFoundException;
import com.Practice.Blog.Exceptions.ResourceNotFoundException;
import com.Practice.Blog.Models.Category;
import com.Practice.Blog.Models.Post;
import com.Practice.Blog.Models.User;
import com.Practice.Blog.Payloads.PostDto;
import com.Practice.Blog.Repository.CategoryRepo;
import com.Practice.Blog.Repository.PostRepo;
import com.Practice.Blog.Repository.UserRepo;
import com.Practice.Blog.Services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.Practice.Blog.Config.AppConstants.*;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepo repo ;
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserRepo userRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto , int UserId , int CategoryId) {
        User user = userRepo.findById(UserId).orElseThrow(()-> new ResourceNotFoundException(USER ,ID , UserId));
        Category category = categoryRepo.findById(CategoryId).orElseThrow(() -> new CategoryNotFoundException(CATEGORY , CategoryId));
        Post po = this.dtoToPost(postDto);
        po.setImageName(DEFAULT_IMAGE_NAME);
        po.setAddedDate(new Date());
        po.setUser(user);
        po.setCategory(category);
        Post savedPost = repo.save(po);
        return this.PostToDto(savedPost);
    }

    @Override
    public PostDto updatePost(PostDto postDto, int id) {
        Post ps = repo.findById(id).orElseThrow(()-> new PostNotFoundException(POST,id));
        ps.setTitle(postDto.getTitle());
        ps.setContent(postDto.getContent());
        ps.setImageName(postDto.getImageName());
        ps.setAddedDate(new Date());
        Post updatedPost = repo.save(ps);
        return this.PostToDto(updatedPost);
    }

    @Override
    public void deletePost(int id) {
        Post ps = repo.findById(id).orElseThrow(()->new PostNotFoundException(POST,id));
        repo.delete(ps);
    }

    @Override
    public PostDto getPost(int id) {
        Post ps = repo.findById(id).orElseThrow(()-> new PostNotFoundException(POST, id));
        return this.PostToDto(ps);
    }

    @Override
    public List<PostDto> post() {
        List<Post> posts = repo.findAll();
        List<PostDto> postDtos = posts.stream().map(this::PostToDto).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getAllPostByCategory(int category_id) {
        Category category = categoryRepo.findById(category_id).orElseThrow(()-> new CategoryNotFoundException(CATEGORY, category_id));
        List<Post> posts = repo.findByCategory(category);
        List<PostDto> postDtos = posts.stream().map(this::PostToDto).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getAllPostsByUser(int user_id) {
        User user = userRepo.findById(user_id).orElseThrow(()->new ResourceNotFoundException(USER ,ID , user_id));
        List<Post> dtos = repo.findByUser(user);
        List<PostDto> postDtos = dtos.stream().map(this::PostToDto).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        List<Post> posts = repo.findByTitleContaining(keyword);
        List<PostDto> postdtos = posts.stream().map(this::PostToDto).collect(Collectors.toList());
        return postdtos;
    }

    public PostDto PostToDto(Post ps){
        PostDto dto = modelMapper.map(ps, PostDto.class);
        return dto;
    }

    public Post dtoToPost(PostDto dto){
        Post po = modelMapper.map(dto ,Post.class);
        return po ;
    }
}
