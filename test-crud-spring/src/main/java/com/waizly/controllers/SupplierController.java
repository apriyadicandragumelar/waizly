package com.waizly.controllers;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waizly.dto.ResponseData;
import com.waizly.dto.SupplierData;
import com.waizly.models.entity.Supplier;
import com.waizly.services.SupplierService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("suppliers")
public class SupplierController {
    
    @Autowired
    private SupplierService supplierService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @Operation(summary = "Create Supplier", description = "Method/function ini adalah insert atau create data baru category")
    public ResponseEntity<ResponseData<Supplier>> create(@Valid @RequestBody SupplierData supplierData, Errors errors) {
        ResponseData<Supplier> responseData = new ResponseData<>();
        if(errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
                
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Supplier supplier = modelMapper.map(supplierData, Supplier.class);
        responseData.setStatus(true);
        responseData.setData(supplierService.create(supplier));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    @Operation(summary = "Get Supplier", description = "Method/function ini adalah menampilkan data supplier")
    public Iterable<Supplier> findAll() {
        return supplierService.findAll();
    }


    @GetMapping("{name}") 
    @Operation(summary = "Find Supplier", description = "Method/function ini adalah mencari data supplier berdasarkan nama")
    public List<Supplier> findByName(@PathVariable("name")String name) {
        return supplierService.findByName("%"+name+"%");
    }

    @PutMapping("{id}")
    @Operation(summary = "Update Supplier", description = "Method/function ini adalah update data supplier")
    public ResponseEntity<ResponseData<Supplier>> update(@PathVariable("id") Long supplierId, @Valid @RequestBody SupplierData supplierData, Errors errors) {
        ResponseData<Supplier> responseData = new ResponseData<>();
        if(errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
                
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        //modelMappper
        Supplier supplier = modelMapper.map(supplierData, Supplier.class);
        responseData.setStatus(true);
        responseData.setData(supplierService.update(supplierId, supplier));
        return ResponseEntity.ok(responseData);
    }   
}

