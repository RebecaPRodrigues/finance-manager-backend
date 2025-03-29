//package com.exemplo.webappfinancemanager.controller;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.exemplo.webappfinancemanager.config.TokenService;
//import com.exemplo.webappfinancemanager.dto.AuthDTO;
//import com.exemplo.webappfinancemanager.dto.LoginResponseDTO;
//import com.exemplo.webappfinancemanager.entity.User;
//import com.exemplo.webappfinancemanager.repository.UserRepository;
//
//import io.swagger.v3.oas.annotations.parameters.RequestBody;
//import jakarta.validation.Valid;
//
//@RestController
//@RequestMapping
//public class AuthController {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private TokenService tokenService;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @PostMapping("/auth")
//    public ResponseEntity login(@RequestBody @Valid AuthDTO data) {
//        var auth = authenticationManager.authenticate(
//            new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword())
//        );
//
//        var user = (User) auth.getPrincipal();
//        var token = tokenService.generateToken(user);
//
//        var response = new LoginResponseDTO(
//        	    user.getId(),
//        	    user.getEmail(),
//        	    user.isAdmin() ? "ADMIN" : "USER",
//        	    token
//        	);
//
//        	return ResponseEntity.ok(response);
//
//    }
//
//}
//
