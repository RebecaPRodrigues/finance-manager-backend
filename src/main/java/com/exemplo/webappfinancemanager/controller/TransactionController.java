package com.exemplo.webappfinancemanager.controller;

import com.exemplo.webappfinancemanager.model.Transaction;
import com.exemplo.webappfinancemanager.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transacoes")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getTransactionsByUser(@PathVariable Long userId) {
        List<Transaction> transactions = transactionRepository.findByUserId(userId);
        return ResponseEntity.ok(transactions);
    }

    @PostMapping
    public ResponseEntity<?> createTransaction(@RequestBody Transaction transaction) {
        Transaction savedTransaction = transactionRepository.save(transaction);
        return ResponseEntity.ok(savedTransaction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
        if (optionalTransaction.isPresent()) {
            Transaction existingTransaction = optionalTransaction.get();
            existingTransaction.setType(transaction.getType());
            existingTransaction.setAmount(transaction.getAmount());
            existingTransaction.setTransactionWith(transaction.getTransactionWith());
            existingTransaction.setDescription(transaction.getDescription());
            existingTransaction.setDate(transaction.getDate());
            existingTransaction.setCategory(transaction.getCategory());
            existingTransaction.setPaymentMethod(transaction.getPaymentMethod());
            transactionRepository.save(existingTransaction);
            return ResponseEntity.ok(existingTransaction);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long id) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
        if (optionalTransaction.isPresent()) {
            transactionRepository.delete(optionalTransaction.get());
            return ResponseEntity.ok().body("Transaction deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
