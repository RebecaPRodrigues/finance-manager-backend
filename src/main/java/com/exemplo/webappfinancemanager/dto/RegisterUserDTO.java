package com.exemplo.webappfinancemanager.dto;

import com.exemplo.webappfinancemanager.entity.UserRole;

public record RegisterUserDTO(String username, String email, String password, UserRole role) {
}
