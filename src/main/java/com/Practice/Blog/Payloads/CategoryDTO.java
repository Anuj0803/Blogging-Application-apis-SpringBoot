package com.Practice.Blog.Payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
public class CategoryDTO {

    private int categoryId;
    @NotEmpty(message = "Title cannot be empty")
    private String categoryTitle;
    @NotEmpty
    @Size(min = 10 , max = 50 ,message = "Description should be between 10 to 50 words")
    private String categoryDescription;
}
