package com.waizly.models.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.waizly.models.entity.AppUser;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);
    
}
