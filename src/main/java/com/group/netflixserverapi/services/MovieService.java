package com.group.netflixserverapi.services;

import com.group.netflixserverapi.models.Movie;
import com.group.netflixserverapi.models.MovieTemplate;
import javassist.NotFoundException;

import java.util.List;

public interface MovieService {
    List<Movie> findAll();
    List<Movie> findAllByOwner(Long owner);
    List<Movie> findByCategoryAndType(Long categoryId, Movie.MovieType type) throws Exception;
    Movie create(String identificationNumber, MovieTemplate movie) throws NotFoundException;
    Movie update(Long movieId, String identificationNumber, MovieTemplate movie) throws NotFoundException;
    void delete(String identificationNumber, Long movieId) throws NotFoundException;

}
