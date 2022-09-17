package com.Practice.Blog.Exceptions;

public class PostNotFoundException extends RuntimeException{

    String message;
    int id ;
    public PostNotFoundException(String message, int id) {
        super("Post Not found with Id "+id);
        this.message =message;
        this.id = id ;
    }
}
