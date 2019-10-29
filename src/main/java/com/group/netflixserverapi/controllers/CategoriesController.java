package com.group.netflixserverapi.controllers;

import com.group.netflixserverapi.models.Category;
import com.group.netflixserverapi.models.Movie;
import com.group.netflixserverapi.services.CategoryService;
import com.group.netflixserverapi.services.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(name = "categories")
public class CategoriesController {
    private final CategoryService categoryService;

    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> findAll(){
        return categoryService.findCategories();
    }
}
