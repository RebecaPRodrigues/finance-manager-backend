package com.exemplo.webappfinancemanager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.exemplo.webappfinancemanager.entity.UserRole;

public record RegisterUserDTO(
    @JsonProperty("username") String userName,
    String email,
    String password,
    UserRole role,
    String imageUrl
) {}
