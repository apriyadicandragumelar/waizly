package com.waizly.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waizly.dto.AppUserData;
import com.waizly.dto.ResponseData;
import com.waizly.models.entity.AppUser;
import com.waizly.services.AppUserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("users")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<ResponseData<AppUser>> register(@RequestBody AppUserData userData) {
        ResponseData<AppUser> responseData = new ResponseData<>();
        AppUser appUser = modelMapper.map(userData, AppUser.class);
        responseData.setData(appUserService.registerUser(appUser));
        responseData.setStatus(true);
        responseData.getMessages().add("AppUser save");
        return ResponseEntity.ok(responseData);
    }
    
}
