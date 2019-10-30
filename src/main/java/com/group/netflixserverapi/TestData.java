package com.group.netflixserverapi;

import com.group.netflixserverapi.models.Category;
import com.group.netflixserverapi.models.Movie;
import com.group.netflixserverapi.repositories.CategoryRepository;
import com.group.netflixserverapi.repositories.MovieRepository;
import com.group.netflixserverapi.repositories.SubscriberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class TestData implements CommandLineRunner {

    private final MovieRepository movieRepository;
//    private final SubscriberRepository subscriberRepository;
    private  final CategoryRepository categoryRepository;

    public TestData(MovieRepository movieRepository, CategoryRepository categoryRepository) {
        this.movieRepository = movieRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Category first_category = categoryRepository.save(new Category("Comedy"));
        Category second_category = categoryRepository.save(new Category("Animation"));
        Category third_category = categoryRepository.save(new Category("Drama"));
        Category fourth_category = categoryRepository.save(new Category("Action"));


        categoryRepository.save(first_category);
        categoryRepository.save(second_category);
        categoryRepository.save(third_category);
        categoryRepository.save(fourth_category);

        Set<Category> categories = new HashSet<>();
        Set<Category> categories1 = new HashSet<>();
        Set<Category> categories2 = new HashSet<>();
        Set<Category> categories3 = new HashSet<>();
        categories.add(first_category);
        categories1.add(second_category);
        categories2.add(third_category);
        categories3.add(fourth_category);

        Movie movie1 = new Movie("Lego", "2018", Movie.MovieType.original, categories1);
        Movie movie2 = new Movie("Avengers Endgame", "2019", Movie.MovieType.original, categories3);

        movieRepository.save(movie1);
        movieRepository.save(movie2);





    }
}
