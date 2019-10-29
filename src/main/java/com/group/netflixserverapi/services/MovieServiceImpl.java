package com.group.netflixserverapi.services;

import com.group.netflixserverapi.models.Category;
import com.group.netflixserverapi.models.Movie;
import com.group.netflixserverapi.models.MovieTemplate;
import com.group.netflixserverapi.models.Subscriber;
import com.group.netflixserverapi.repositories.CategoryRepository;
import com.group.netflixserverapi.repositories.MovieRepository;
import com.group.netflixserverapi.repositories.SubscriberRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final SubscriberRepository subscriberRepository;
    private final CategoryRepository categoryRepository;

    public MovieServiceImpl(MovieRepository movieRepository, SubscriberRepository subscriberRepository, CategoryRepository categoryRepository) {
        this.movieRepository = movieRepository;
        this.subscriberRepository = subscriberRepository;
        this.categoryRepository = categoryRepository;
    }


    //to list all movies
    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    // to list all movies under a certain owner
    @Override
    public List<Movie> findAllByOwner(Long owner) {
        return movieRepository.findByOwnerId(owner);
    }
//
//    @Override
//    public List<Movie> findByCategory(String categoryname) throws NotFoundException {
//        Category foundCategory = categoryRepository.findById()
//        return null;
//    }

    //to search for movies by category and type
    @Override
    public List<Movie> findByCategoryAndType(Long categoryId, Movie.MovieType type) throws Exception {
        Category foundCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("The given category does not exist"));
        List<Movie> movies = movieRepository.findAllCategoryAndType(foundCategory, type);
        if(type == Movie.MovieType.suggested || type == Movie.MovieType.original)
            return movies;
        else
            throw new Exception("Movie type can only be 'original' or 'suggested'");
    }

    //for a subscriber to suggest a movie
    @Override
    public Movie create(String identificationNumber, MovieTemplate movie) throws NotFoundException {
        Subscriber foundSubscriber = subscriberRepository.findByIdentificationNumber(identificationNumber).orElseThrow(() -> new NotFoundException("User with given identification number does not exist"));
        List<Category> savedCategories =  categoryRepository.saveAll(movie.getCategories());
        Set<Category> categories = new HashSet<>(savedCategories);
        return movieRepository.save(new Movie(movie.getName(),Movie.MovieType.suggested,movie.getYearOfRelease(),foundSubscriber, categories));
    }

    // a movie suggested by a specific subscriber can be updated
    @Override
    public Movie update(Long movieId, String identificationNumber, MovieTemplate movie) throws NotFoundException {
        Subscriber subscriber = subscriberRepository.findByIdentificationNumber(identificationNumber)
                .orElseThrow(() -> new NotFoundException("User with given identification number does not exist"));
        Movie updatedmovie = movieRepository.findByOwnerIdAndId(subscriber.getId(), movieId)
                .orElseThrow(() -> new NotFoundException("The movie does not exist or does not belong to the subscriber."+ identificationNumber));
        Set<Category> categories = new HashSet<>(categoryRepository.saveAll(movie.getCategories()));
        updatedmovie.setName(movie.getName());
        updatedmovie.setYearOfRelease(movie.getYearOfRelease());
        updatedmovie.setCategories(categories);
        return movieRepository.save(updatedmovie);
    }

    //a movie suggested by a  specific subscriber can be deleted
    @Override
    public void delete(String identificationNumber, Long movieId) throws NotFoundException {
        Subscriber foundSubscriber =  subscriberRepository.findByIdentificationNumber(identificationNumber)
                .orElseThrow(() -> new NotFoundException("User with given identification number does not exist"));
        Movie deletedMovie =  movieRepository.findByOwnerIdAndId(foundSubscriber.getId(), movieId)
                 .orElseThrow(() -> new NotFoundException("The movie does not exist or does not belong to the subscriber."+ identificationNumber));
        movieRepository.deleteByOwnerIdAndId(foundSubscriber.getId(),movieId);
    }
}
