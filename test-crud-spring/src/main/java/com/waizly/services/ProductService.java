package com.waizly.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waizly.models.entity.Product;
import com.waizly.models.entity.Supplier;
import com.waizly.models.repo.ProductRepo;
import com.waizly.models.repo.SupplierRepo;


@Service
@Transactional
public class ProductService {
    
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private SupplierRepo supplierRepo;

    // get all product
    public Iterable<Product> findAll() {
        return productRepo.findAll();
    }

    //post 
    public Product save(Product product) {
        return productRepo.save(product);
    }

    //put
    public Product update(@Valid Product newProduct, Long productId) {
        Optional<Product> product = productRepo.findById(productId);
        if(product.isEmpty()) {
            return null;
        }
        Product oldProduct = product.get();
        oldProduct.setName(newProduct.getName());
        oldProduct.setDescription(newProduct.getDescription());
        oldProduct.setPrice(newProduct.getPrice());
        return oldProduct;
    }

    //Get by name
    public List<Product> findProductByName(String nameProduct) {
        return productRepo.findProductByName("%"+nameProduct+"%");
    }

    //add id product ke id supplier
    public Iterable<Product> addSupplier(Long productId, Long idSupplier) {
        Product product = productRepo.findById(productId).get();
        Supplier supplier = supplierRepo.findById(idSupplier).get();
        product.getSuppliers().add(supplier);
        save(product);
        return productRepo.findAll();   
    }

    //delete
    public void deleteId(Long id) {
        productRepo.deleteById(id);
    }
}
