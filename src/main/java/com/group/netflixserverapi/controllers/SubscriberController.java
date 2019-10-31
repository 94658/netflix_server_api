package com.group.netflixserverapi.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group.netflixserverapi.errors.ErrorFormatter;
import com.group.netflixserverapi.models.Movie;
import com.group.netflixserverapi.models.Subscriber;
import com.group.netflixserverapi.repositories.MovieRepository;
import com.group.netflixserverapi.repositories.SubscriberRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "subscribers")
public class SubscriberController {

    private final SubscriberRepository subscriberRepository;
    private final MovieRepository movieRepository;
    private final ObjectMapper mapper = new ObjectMapper();

    public SubscriberController(SubscriberRepository subscriberRepository, MovieRepository movieRepository) {
        this.subscriberRepository = subscriberRepository;
        this.movieRepository = movieRepository;
    }

    /**
     * To register new Subscribers
     *
     * @param subscriber
     * @return String
     */
    @PostMapping
    public String register(@RequestBody Subscriber subscriber) throws JsonProcessingException {

        Optional<Subscriber> newSubscriber = subscriberRepository.findByIdentificationNumber(subscriber.getIdentificationNumber());

        if (newSubscriber.isPresent())
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(new ErrorFormatter("This subscriber exists"));
        else if (subscriber.getIdentificationNumber() == null) {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(new ErrorFormatter("identificationNumber cannot be null"));
        }

        subscriberRepository.save(subscriber);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(subscriber);

    }

    @GetMapping(value = "{subscriberId}/movies")
    public List<Movie> userMovies(@PathVariable Long subscriberId) {
        return movieRepository.findByContentOwnerId(subscriberId);
    }
}
