package com.waizly.controllers;

import java.util.List;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import com.waizly.dto.CategoryData;
import com.waizly.dto.ResponseData;
import com.waizly.models.entity.Category;
import com.waizly.services.CategoryService;



@RestController
@RequestMapping("categories")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @Operation(summary = "Create Category", 
               description = "Method/function ini adalah insert data atau create data category ")
    public ResponseEntity<ResponseData<Category>> create(@Valid @RequestBody CategoryData categoryData, Errors errors) {
        ResponseData<Category> responseData = new ResponseData<>();
        if(errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Category category = modelMapper.map(categoryData, Category.class);
        responseData.setStatus(true);
        responseData.setData(categoryService.create(category));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    @Operation(summary = "Get Category", 
               description = "Method/function ini adalah menampilkan data category ")
    public Iterable<Category> findAll(){
        return categoryService.findAll();
    }

    @GetMapping("{name}")
    @Operation(summary = "Get Category nama",
               description = "Method/function ini adalah mencari category berdasarkan nama")
    public List<Category> findById(String name) {
        return categoryService.findByName("%"+name+"%");
    }

    @PutMapping("{id}")
    @Operation(summary = "Update Category", 
              description = "Method/function ini adalah update data category")
    public ResponseEntity<ResponseData<Category>> update(@PathVariable("id") Long categoryId, @Valid @RequestBody 
                                                          CategoryData categoryData, Errors errors) {
        ResponseData<Category> responseData = new ResponseData<>();
        if(errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Category category = modelMapper.map(categoryData, Category.class);
        responseData.setStatus(true);
        responseData.setData(categoryService.update(categoryId, category));
        return ResponseEntity.ok(responseData);
    }
}
