package com.waizly.models.repo;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.waizly.models.entity.Category;


public interface CategoryRepo extends JpaRepository <Category, Long> {

    @Query("SELECT c FROM Category c WHERE c.name Like :name")
    public List<Category> findCategoryByName(@PathParam("name") String name);

}
