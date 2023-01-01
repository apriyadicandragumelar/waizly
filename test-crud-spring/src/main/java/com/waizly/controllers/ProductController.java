package com.waizly.controllers;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import com.waizly.dto.ResponseData;
import com.waizly.models.entity.Product;
import com.waizly.services.ProductService;



@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    @Operation(summary = "Get Product", 
               description = "Method/function ini adalah menampilkan seluruh data product")
    public Iterable<Product> getAll() {
        return productService.findAll();
    }

    @PostMapping("/{idProduct}/add/{idSupplier}")
    @Operation(summary = "create Product - Supplier", 
               description = "Method/function ini adalah create/insert data product berdasarkan id kedalam id supplier")
    public Iterable<Product> addSupplier(@PathVariable("idProduct") Long productId, @PathVariable("idSupplier") Long idSupplier) {
        return productService.addSupplier(productId , idSupplier);
    }

    @GetMapping("{name}")
    @Operation(summary = "Find Product", 
               description = "Method/function ini adalah mencari product berdasarkan nama")
    public List<Product> getByName(@PathVariable("name")String nameProduct) {
        return productService.findProductByName(nameProduct);
    }

    @PostMapping()
    @Operation(summary = "Create id Product", 
               description = "Method/function ini meng-create data product kedalam id category")
    public ResponseEntity<ResponseData<Product>> create (@Valid @RequestBody Product product, Errors errors) {
        ResponseData<Product> responseData = new ResponseData<>();
        if(errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
               responseData.getMessages().add(error.getDefaultMessage());
            } 
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setData(productService.save(product));
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("{id}")
    @Operation(summary = "Update Product", 
               description = "Method/function ini meng-update data product")
    public ResponseEntity<ResponseData<Product>> update(@PathVariable("id") Long id, @Valid @RequestBody 
                                                        Product product, Errors errors) {
        ResponseData<Product> responseData = new ResponseData<>();
        if(errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setData(productService.update(product, id));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete all Relationship", 
               description = "Method/function ini men-delete semua entitas, baik many to one maupun many to many")
    public void deleteById(@PathVariable("id") Long id) {
        productService.deleteId(id);
    } 
}
