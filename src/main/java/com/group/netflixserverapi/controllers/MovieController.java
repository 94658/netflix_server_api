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

    /**
     * Deletes Movie belonging to a subscriber,
     *
     * @param identificationNumber
     * @param movieId
     * @throws NotFoundException
     */
    @DeleteMapping(value = "{movieId}")
    public void delete(@RequestHeader("identificationNumber") String identificationNumber, @PathVariable Long movieId) throws NotFoundException {
        movieService.delete(identificationNumber, movieId);
    }
}
