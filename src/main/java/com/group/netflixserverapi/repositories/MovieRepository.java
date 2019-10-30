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
public interface MovieRepository extends JpaRepository <Movie,Long> {

    List<Movie> findByContentownerId(Long owner_id);
    Optional<Movie> findByContentownerIdAndId(Long owner_id, Long id);
    List <Movie> findByCategoriesAndAndType(Category category, Movie.MovieType type);

    void deleteById(Long id); //delete by id of the movie

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM movies WHERE contentowner=?1 AND id=?2", nativeQuery = true)
    void deleteByOwnerIdAndId(Long ownerId, Long id);




}
