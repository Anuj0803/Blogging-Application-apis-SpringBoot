package com.Practice.Blog.Exceptions;

import com.Practice.Blog.Payloads.apiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<apiResponse> ResouceNotFoundExceptionHandler(ResourceNotFoundException rs){
        String message = rs.getMessage();
        apiResponse ap = new apiResponse(message , false);
        return new ResponseEntity<apiResponse>(ap, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<Map <String,String>> handleMethodArgNotValidException(MethodArgumentNotValidException ar){
        Map<String,String> resp = new HashMap<>();
        ar.getBindingResult().getAllErrors().forEach((error) ->{
            String fieldName = ((FieldError)error).getField();
            String errorMessagge = ((FieldError)error).getDefaultMessage();
            resp.put(fieldName,errorMessagge);
        });
        return  new ResponseEntity<>(resp ,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<apiResponse> CategoryNotFoundExceptionHandler(CategoryNotFoundException ex){
        String message = ex.getMessage();
        apiResponse ap = new apiResponse(message ,false);
        return new ResponseEntity<apiResponse>(ap,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<apiResponse> PostNotFoundExceptionHandler(PostNotFoundException ps){
        String message = ps.getMessage();
        apiResponse ap = new apiResponse(message ,false);
        return new ResponseEntity<>(ap , HttpStatus.NOT_FOUND);
    }
}
