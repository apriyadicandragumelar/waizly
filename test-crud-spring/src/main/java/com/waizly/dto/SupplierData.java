package com.waizly.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SupplierData {
    
    @NotBlank(message = "Nama harus diisi")
    private String name;

    @NotBlank(message = "Alamat harus diisi")
    private String address;

    @NotBlank(message = "Nama harus diisi")
    @Email(message = "Email tidak benar")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
