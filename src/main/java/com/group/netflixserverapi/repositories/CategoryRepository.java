package com.group.netflixserverapi.repositories;

import com.group.netflixserverapi.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository <Category, Long> {

}
