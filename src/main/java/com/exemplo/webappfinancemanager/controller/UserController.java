package com.exemplo.webappfinancemanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.webappfinancemanager.dto.RegisterUserDTO;
import com.exemplo.webappfinancemanager.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")

public class UserController {
	
	@Autowired
    private UserService service;

	@PostMapping
	public ResponseEntity<?> register(@RequestBody @Valid RegisterUserDTO data) {
		if (this.service.findByUserName(data.username()) != null)
			return ResponseEntity.badRequest().build();


		return ResponseEntity.ok(this.service.save(data));
	}
	
	@GetMapping
	public ResponseEntity<List<?>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}

}
