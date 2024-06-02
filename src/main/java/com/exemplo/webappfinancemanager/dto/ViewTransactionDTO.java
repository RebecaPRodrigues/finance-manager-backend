package com.exemplo.webappfinancemanager.dto;

public record ViewTransactionDTO(String id, String type, Double amount, String transactionWith, String description, String date, String category, String paymentMethod) {



}
