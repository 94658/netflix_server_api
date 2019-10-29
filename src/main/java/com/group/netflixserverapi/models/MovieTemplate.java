package com.group.netflixserverapi.models;

import java.util.Set;

public class MovieTemplate {
    private String name;
    private String yearOfRelease;
    private Set<Category> categories;
    private Movie.MovieType type;
    private Long ownerId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(String yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Movie.MovieType getType() {
        return type;
    }

    public void setType(Movie.MovieType type) {
        this.type = type;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
