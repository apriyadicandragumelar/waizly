package com.waizly.dto;

import javax.validation.constraints.NotBlank;

public class CategoryData {
    
    @NotBlank(message = "Nama tidak boleh kosong")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }  
}