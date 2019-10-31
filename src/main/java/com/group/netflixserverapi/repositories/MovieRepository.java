package com.group.netflixserverapi.repositories;

import com.group.netflixserverapi.models.Category;
import com.group.netflixserverapi.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByContentOwnerId(Long owner_id);

    Optional<Movie> findByContentOwnerIdAndId(Long owner_id, Long id);

    List<Movie> findByCategoriesAndType(Category category, Movie.MovieType type);

    void deleteById(Long id); //delete by id of the movie

    @Transactional
    void deleteMovieByContentOwner_idAndId(Long ownerId, Long id);


}
