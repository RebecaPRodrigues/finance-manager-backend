package com.exemplo.webappfinancemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.exemplo.webappfinancemanager.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
    
    UserDetails findByUserName(String userName);

}
