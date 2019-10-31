package com.group.netflixserverapi.controllers;

import com.group.netflixserverapi.models.Movie;
import com.group.netflixserverapi.models.MovieTemplate;
import com.group.netflixserverapi.models.Subscriber;
import com.group.netflixserverapi.services.MovieService;
import com.group.netflixserverapi.services.SubscriberService;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "subscribers")
public class SubscriberController {

    private final SubscriberService subscriberService;
    private final MovieService movieService;

    public SubscriberController(SubscriberService subscriberService, MovieService movieService) {
        this.subscriberService = subscriberService;
        this.movieService = movieService;
    }

    /**
     * To register new Subscribers
     *
     * @param subscriber
     * @return String
     */
    @PostMapping
    public Subscriber register(@RequestBody Subscriber subscriber) throws Exception {
        return subscriberService.create(subscriber);

    }

    @GetMapping(value = "{subscriberId}/movies")
    public List<Movie> userMovies(@PathVariable Long subscriberId) {
        return movieService.findAllByOwner(subscriberId);
    }

    /**
     * Create a suggested Movie by a subscriber
     *
     * @param identificationNumber
     * @param movie
     * @return a Movie object
     * @throws NotFoundException
     */
    @PostMapping(value = "{identificationNumber}/movies")
    public Movie create(@PathVariable("identificationNumber") String identificationNumber, @RequestBody MovieTemplate movie) throws NotFoundException {
        return movieService.create(identificationNumber, movie);
    }

    /**
     * Update Movie details belonging to a subscriber, only the owner subscriber can update
     *
     * @param identificationNumber
     * @param movieId
     * @param movie
     * @return Movie object
     * @throws NotFoundException
     */
    @PatchMapping(value = "{identificationNumber}/movies/{movieId}")
    public Movie update(@PathVariable("identificationNumber") String identificationNumber, @PathVariable Long movieId, @RequestBody MovieTemplate movie) throws NotFoundException {
        return movieService.update(movieId, identificationNumber, movie);
    }

    /**
     * Deletes Movie belonging to a subscriber,
     *
     * @param identificationNumber
     * @param movieId
     * @throws NotFoundException
     */
    @DeleteMapping(value = "{identificationNumber}/movies/{movieId}")
    public void delete(@PathVariable("identificationNumber") String identificationNumber, @PathVariable Long movieId) throws NotFoundException {
        movieService.delete(identificationNumber, movieId);
    }
}
