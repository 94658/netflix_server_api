package com.group.netflixserverapi.controllers;

import com.group.netflixserverapi.models.Movie;
import com.group.netflixserverapi.services.MovieService;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> findAll() {
        return movieService.findAll();
    }
}
