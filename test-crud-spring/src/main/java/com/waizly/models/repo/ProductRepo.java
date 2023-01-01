package com.waizly.models.repo;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.waizly.models.entity.Product;



public interface ProductRepo extends JpaRepository<Product, Long> {

    /* custom query */
    @Query("SELECT p FROM Product p WHERE p.name Like :name")
    public List<Product> findProductByName(@PathParam("name") String name);

   
}
