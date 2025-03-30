package com.exemplo.webappfinancemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {
    private String id;
    private String username;
    private String email;
    private String role;
    private String token;
}
