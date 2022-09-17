package com.Practice.Blog.Controllers;

import com.Practice.Blog.Payloads.PostDto;
import com.Practice.Blog.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    PostService service;

    @PostMapping("/user/{userId}/category/{categoryId}/Post")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto , @PathVariable("userId") int uid , @PathVariable("categoryId") int cid){
        PostDto dto = service.createPost(postDto , uid ,cid);
        return new ResponseEntity<PostDto>(dto , HttpStatus.CREATED);
    }

    @PutMapping("/Update/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto , @PathVariable("postId") int pid){
       PostDto dto = service.updatePost(postDto , pid);
       return new ResponseEntity<PostDto>(dto ,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePost(@PathVariable("id") int id){
        service.deletePost(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") int id){
        PostDto ps = service.getPost(id);
        return new ResponseEntity<>(ps , HttpStatus.FOUND);

    }

    @GetMapping("/allPost")
    public List<PostDto> getAllPosts(){
        List<PostDto> postDtos =service.post();
        return postDtos ;
    }

    //Get Post by User I'd
    @GetMapping("findAll/{userid}")
    public List<PostDto> getByUserId(@PathVariable("userid") int id){
        List<PostDto> postDtos =service.getAllPostsByUser(id);
        return postDtos ;
    }

    //Get Post by Category
    @GetMapping("findByCategory/{categoryid}")
    public List<PostDto> getByCategoryId(@PathVariable("categoryid") int id){
        List<PostDto> postDtos = service.getAllPostByCategory(id);
        return postDtos;
    }

    //Search Post By Title
    @GetMapping("/Title/{title}")
    public ResponseEntity<List<PostDto>> searchPost(@PathVariable("title") String title){
        List<PostDto> postDtos = service.searchPost(title);
        return new ResponseEntity<>(postDtos , HttpStatus.FOUND);
    }
}
