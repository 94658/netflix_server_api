package com.group.netflixserverapi.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    public enum MovieType {
        original,
        suggested
    }

    @Enumerated(EnumType.STRING)
    private MovieType type;

    @Column(name = "year_of_release")
    private String yearOfRelease;

    @OneToOne
    @JoinColumn(name = "owner_id")
    private Subscriber contentOwner;

    @ManyToMany
    @JoinTable(
            name = "movie_categories",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;

    public Movie() {
    }

    public Movie(String name, String yearOfRelease, MovieType type, Set<Category> categories) {
        this.name = name;
        this.type = type;
        this.yearOfRelease = yearOfRelease;
        this.categories = categories;
    }

    public Movie(String name, MovieType type, String yearOfRelease, Subscriber contentOwner, Set<Category> categories) {
        this.name = name;
        this.type = type;
        this.yearOfRelease = yearOfRelease;
        this.contentOwner = contentOwner;
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MovieType getType() {
        return type;
    }

    public void setType(MovieType type) {
        this.type = type;
    }

    public String getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(String yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public Subscriber getContentOwner() {
        return contentOwner;
    }

    public void setContentOwner(Subscriber contentOwner) {
        this.contentOwner = contentOwner;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}

