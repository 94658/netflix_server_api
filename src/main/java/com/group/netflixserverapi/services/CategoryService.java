package com.group.netflixserverapi.services;

import com.group.netflixserverapi.models.Category;

import java.util.List;


public interface CategoryService {
    List<Category> findCategories();
}
