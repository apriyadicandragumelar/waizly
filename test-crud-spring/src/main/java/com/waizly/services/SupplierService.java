package com.waizly.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waizly.models.entity.Supplier;
import com.waizly.models.repo.SupplierRepo;



@Service
@Transactional
public class SupplierService {
    
    @Autowired
    private SupplierRepo supplierRepo;

    
    //post
    public Supplier create(Supplier supplier) {
        return supplierRepo.save(supplier);
    }

    //put
    public Supplier update(Long supplierId, Supplier newSupplier) {
        Optional<Supplier> supplier = supplierRepo.findById(supplierId);
        if(supplier.isEmpty()) {
            return null;
        }
        Supplier oldSupplier = supplier.get();
        oldSupplier.setName(newSupplier.getName());
        oldSupplier.setAddress(newSupplier.getAddress());
        oldSupplier.setEmail(newSupplier.getEmail());
        return oldSupplier;
    }
    
    //get id
    public List<Supplier> findByName(String name) {
        return supplierRepo.findBySupplierName(name);
    }

    // get all
    public Iterable<Supplier> findAll() {
        return supplierRepo.findAll();
    }
  
}
