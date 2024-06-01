package com.exemplo.webappfinancemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.webappfinancemanager.config.TokenService;
import com.exemplo.webappfinancemanager.dto.AuthenticationDTO;
import com.exemplo.webappfinancemanager.dto.LoginResponseDTO;
import com.exemplo.webappfinancemanager.entity.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
	
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private TokenService tokenService;

    @PostMapping()
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

}