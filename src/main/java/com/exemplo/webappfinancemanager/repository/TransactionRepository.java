package com.exemplo.webappfinancemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exemplo.webappfinancemanager.entity.Transaction;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    List<Transaction> findByUserId(String userId);
}
