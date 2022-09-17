package com.Practice.Blog.Controllers;

import com.Practice.Blog.Payloads.CategoryDTO;
import com.Practice.Blog.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryService service;

    @PostMapping("/")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO ct){
       CategoryDTO ctreatedDto = service.createCategory(ct);
        return new ResponseEntity<>(ctreatedDto , HttpStatus.CREATED);
    }

    @PutMapping("/Category/{categoryId}")
    public  ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO ct , @PathVariable("categoryId") int id){
        CategoryDTO updatedDto = service.updateCategory(ct, id);
        return new ResponseEntity<>(updatedDto , HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/Category/{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") int id){
        service.deleteCategory(id);
    }

    @GetMapping("/Category/{categoryId}")
    public ResponseEntity<CategoryDTO> get(@PathVariable("categoryId") int id){
        CategoryDTO dto = service.getCategory(id);
        return new ResponseEntity<>(dto ,HttpStatus.FOUND);
    }

    @GetMapping("/Category")
    public List<CategoryDTO> getAllCategoryDto(){
        List<CategoryDTO> dtos = service.getallCategory();
        return  dtos ;
    }
}
