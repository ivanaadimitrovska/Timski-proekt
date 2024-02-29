package com.example.timski.repository;


import com.example.timski.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByNameLike(String text);
    void deleteAllByName(String name);
}
