package com.Practice.Blog.Exceptions;

public class CategoryNotFoundException extends RuntimeException {

    String message;
    int id;
    public CategoryNotFoundException(String message, int id) {
        super("Category Not found with id "+id);
        this.message = message;
        this.id = id;
    }
}
