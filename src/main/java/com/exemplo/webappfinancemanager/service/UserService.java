package com.exemplo.webappfinancemanager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.exemplo.webappfinancemanager.dto.RegisterUserDTO;
import com.exemplo.webappfinancemanager.dto.ViewUserDTO;
import com.exemplo.webappfinancemanager.entity.User;
import com.exemplo.webappfinancemanager.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;

	public UserDetails findByUserName(String login) {
		return repository.findByUserName(login);
	}
	
	public List<ViewUserDTO> findAll() {
		return repository.findAll().stream().map(user -> parseToDTO(user)).collect(Collectors.toList());
	}

	public ViewUserDTO save(RegisterUserDTO data) {
		// TODO Auto-generated method stub

		String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
		User newUser = new User(data.username(), encryptedPassword, data.email(), data.role());
	
		
		repository.save(newUser);
		
		return parseToDTO(newUser);
	}
	
	private ViewUserDTO parseToDTO(User user) {
		return new ViewUserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getImageUrl(), user.isAdmin());
	}

}
