package com.waizly.models.repo;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.waizly.models.entity.Supplier;


public interface SupplierRepo extends JpaRepository<Supplier, Long> {
    
    @Query("SELECT s FROM Supplier s WHERE s.name LIKE :name")
    public List<Supplier> findBySupplierName(@PathParam("name") String name);
}
