package com.Practice.Blog.Repository;

import com.Practice.Blog.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {

}
