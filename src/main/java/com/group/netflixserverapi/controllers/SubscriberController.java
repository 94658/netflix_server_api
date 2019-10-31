package com.group.netflixserverapi.controllers;

import com.group.netflixserverapi.models.Movie;
import com.group.netflixserverapi.models.Subscriber;
import com.group.netflixserverapi.services.MovieService;
import com.group.netflixserverapi.services.SubscriberService;
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
}
