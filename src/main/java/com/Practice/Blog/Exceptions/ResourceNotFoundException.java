package com.Practice.Blog.Exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

    String resource;
    String fieldName;
    int fieldValue ;

    public ResourceNotFoundException(String resource, String fieldName, int fieldValue) {
        super("User Not Found with id " + fieldValue);
        this.resource = resource;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
