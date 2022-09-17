package com.Practice.Blog.Services;

import com.Practice.Blog.Payloads.CategoryDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO createCategory(CategoryDTO dto);

    CategoryDTO updateCategory(CategoryDTO dto , int categoryId);

    void deleteCategory(int categoryId);

    CategoryDTO getCategory(int categoryId);

    List<CategoryDTO> getallCategory();

}
