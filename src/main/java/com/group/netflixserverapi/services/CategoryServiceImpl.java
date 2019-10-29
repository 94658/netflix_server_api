package com.group.netflixserverapi.services;

import com.group.netflixserverapi.models.Category;
import com.group.netflixserverapi.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository ;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findCategories() {
        return  categoryRepository.findAll();
    }
}
