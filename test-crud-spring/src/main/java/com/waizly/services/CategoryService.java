package com.waizly.services;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waizly.models.entity.Category;
import com.waizly.models.repo.CategoryRepo;



@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;


    //post
    public Category create(Category category) {
        return categoryRepo.save(category);
    }

    //put
    public Category update(Long categoryId, Category newCategory) {
        Optional<Category> category = categoryRepo.findById(categoryId);
        if(category.isEmpty()) {
            return null;
        }
        Category oldCategory = category.get();
        oldCategory.setName(newCategory.getName());
        return oldCategory;
    }

    //find name
    public List<Category> findByName(String name) {
        return categoryRepo.findCategoryByName(name);
    }
    
    //find all
    public Iterable<Category> findAll() {
        return categoryRepo.findAll();
    }
}

