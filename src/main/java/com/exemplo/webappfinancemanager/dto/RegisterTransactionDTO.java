package com.exemplo.webappfinancemanager.dto;

import java.time.LocalDate;

import com.exemplo.webappfinancemanager.entity.TransactionCategory;
import com.exemplo.webappfinancemanager.entity.TransactionType;

public record RegisterTransactionDTO(String userId, TransactionType type, Double amount, String transactionWith, String description, LocalDate date, TransactionCategory category, String paymentMethod) {


}
