package com.group.netflixserverapi.controllers;

import com.group.netflixserverapi.models.Movie;
import com.group.netflixserverapi.models.MovieTemplate;
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
    public List<Movie> findAll(){
        return movieService.findAll();
    }

    /**
     * search for a list of movies by category and type
     * @param categoryId
     * @param type
     * @return a list of Movies
     * @throws Exception
     */
    @GetMapping(value = "{categoryId}")
    public List<Movie> findByCategoryAndType(@PathVariable Long categoryId, @RequestParam Movie.MovieType type) throws Exception {
        return movieService.findByCategoryAndType(categoryId, type);
    }

    /**
     * create a suggested Movie by a subscriber
     * @param identificationNumber
     * @param movie
     * @return a Movie object
     * @throws NotFoundException
     */
    @PostMapping
    public Movie create(@RequestHeader("identificationNumber") String identificationNumber, @RequestBody MovieTemplate movie) throws NotFoundException {
        return  movieService.create(identificationNumber, movie);
    }

    /**
     * update Movie details belonging to a subscriber, only the owner subscriber can update
     * @param movieId
     * @param identificationNumber
     * @param movie
     * @return Movie object
     * @throws NotFoundException
     */
    @PatchMapping(value = "{movieId}")
    public Movie update(@PathVariable Long movieId, @RequestHeader("identificationNumber") String identificationNumber, @RequestBody MovieTemplate movie) throws NotFoundException {
        return movieService.update(movieId, identificationNumber, movie);
    }

    /**
     * deletes Movie belonging to a subscriber,
     * @param identificationNumber
     * @param movieId
     * @throws NotFoundException
     */
    @DeleteMapping(value = "{movieId}")
    public void delete(@RequestHeader String identificationNumber, @PathVariable Long movieId) throws NotFoundException {
        movieService.delete(identificationNumber, movieId);
    }
}
