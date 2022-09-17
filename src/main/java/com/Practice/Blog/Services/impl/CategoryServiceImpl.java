package com.Practice.Blog.Services.impl;

import com.Practice.Blog.Exceptions.CategoryNotFoundException;
import com.Practice.Blog.Models.Category;
import com.Practice.Blog.Payloads.CategoryDTO;
import com.Practice.Blog.Repository.CategoryRepo;
import com.Practice.Blog.Services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import static com.Practice.Blog.Config.AppConstants.*;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CategoryRepo repo;

    @Override
    public CategoryDTO createCategory(CategoryDTO dto) {
        Category ct = this.DtoToCategory(dto);
        Category savedct = repo.save(ct);
        return this.CategorytoDto(savedct);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO dto, int id) {
        Category ct = repo.findById(id).orElseThrow(() -> new CategoryNotFoundException(CATEGORY , id));
        ct.setCategoryTitle(dto.getCategoryTitle());
        ct.setCategoryDescription(dto.getCategoryDescription());
        Category ss = repo.save(ct);
        return this.CategorytoDto(ss);
    }

    @Override
    public void deleteCategory(int categoryId) {
        Category ct = repo.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(CATEGORY ,categoryId));
        repo.delete(ct);
    }

    @Override
    public CategoryDTO getCategory(int categoryId) {
        Category ct = repo.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(CATEGORY ,categoryId));
        return this.CategorytoDto(ct);
    }

    @Override
    public List<CategoryDTO> getallCategory() {
        List<Category> ct = repo.findAll();

        List<CategoryDTO> cto = ct.stream().map(this::CategorytoDto).collect(Collectors.toList());
        return cto;
    }

    public CategoryDTO CategorytoDto(Category ct){
       CategoryDTO dto = modelMapper.map(ct ,CategoryDTO.class);
       return  dto ;
    }

    public Category DtoToCategory(CategoryDTO ct){
        Category ca = modelMapper.map(ct, Category.class);
        return  ca;
    }
}
