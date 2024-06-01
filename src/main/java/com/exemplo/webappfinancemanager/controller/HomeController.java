package com.exemplo.webappfinancemanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Welcome to the Home Page!"); // Retorna uma resposta simples
    }

    @GetMapping("/home")
    public ResponseEntity<String> homePage() {
        return ResponseEntity.ok("This is the Home Page!"); // Retorna uma resposta simples
    }

    @GetMapping("/error")
    public ResponseEntity<String> errorPage() {
        return ResponseEntity.ok("An error has occurred."); // Retorna uma resposta simples
    }
}
