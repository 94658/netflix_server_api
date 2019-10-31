package com.group.netflixserverapi.controllers;


import com.group.netflixserverapi.models.Category;
import com.group.netflixserverapi.models.Movie;
import com.group.netflixserverapi.services.CategoryService;
import com.group.netflixserverapi.services.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "categories")
public class CategoriesController {
    private final CategoryService categoryService;
    private final MovieService movieService;

    public CategoriesController(CategoryService categoryService, MovieService movieService) {
        this.categoryService = categoryService;
        this.movieService = movieService;
    }

    @GetMapping
    public List<Category> findAll() {
        return categoryService.findCategories();
    }

    /**
     * Search for a list of movies by category and type
     *
     * @param categoryId
     * @param type
     * @return a list of Movies
     * @throws Exception
     */
    @GetMapping(value = "{categoryId}/movies")
    public List<Movie> findByCategoryAndType(@PathVariable Long categoryId, @RequestParam Movie.MovieType type) throws Exception {
        return movieService.findByCategoryAndType(categoryId, type);
    }
}
