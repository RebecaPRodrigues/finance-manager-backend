package com.exemplo.webappfinancemanager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.webappfinancemanager.dto.RegisterTransactionDTO;
import com.exemplo.webappfinancemanager.dto.ViewTransactionDTO;
import com.exemplo.webappfinancemanager.entity.Transaction;
import com.exemplo.webappfinancemanager.entity.User;
import com.exemplo.webappfinancemanager.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getTransactionsByUser(@PathVariable String userId) {
        List<ViewTransactionDTO> transactions = service.findByUserId(userId);
        return ResponseEntity.ok(transactions);
    }

    @PostMapping
    public ResponseEntity<?> createTransaction(@RequestBody RegisterTransactionDTO transaction) {
    	ViewTransactionDTO savedTransaction = service.save(transaction);
        return ResponseEntity.ok(savedTransaction);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
//        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
//        if (optionalTransaction.isPresent()) {
//            Transaction existingTransaction = optionalTransaction.get();
//            existingTransaction.setType(transaction.getType());
//            existingTransaction.setAmount(transaction.getAmount());
//            existingTransaction.setTransactionWith(transaction.getTransactionWith());
//            existingTransaction.setDescription(transaction.getDescription());
//            existingTransaction.setDate(transaction.getDate());
//            existingTransaction.setCategory(transaction.getCategory());
//            existingTransaction.setPaymentMethod(transaction.getPaymentMethod());
//            transactionRepository.save(existingTransaction);
//            return ResponseEntity.ok(existingTransaction);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable String id) {
        var tranView = service.findById(id);
        if (tranView != null) {
            service.delete(tranView.id());
            Map<String, Boolean> response = new HashMap<>();
            response.put("success", true);
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> updateTransaction(
            @PathVariable String id, 
            @RequestBody RegisterTransactionDTO updatedTransactionDTO) {

        Optional<Transaction> optionalTransaction = Optional.empty();
        if (optionalTransaction.isPresent()) {
            Transaction transaction = optionalTransaction.get();

            Optional<User> optionalUser = Optional.empty();
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();

                transaction.setUser(user);
                transaction.setType(updatedTransactionDTO.type());
                transaction.setAmount(updatedTransactionDTO.amount());
                transaction.setTransactionWith(updatedTransactionDTO.transactionWith());
                transaction.setDescription(updatedTransactionDTO.description());
                transaction.setDate(updatedTransactionDTO.date());
                transaction.setCategory(updatedTransactionDTO.category());
                transaction.setPaymentMethod(updatedTransactionDTO.paymentMethod());

                service.save(transaction);

                Map<String, Boolean> response = new HashMap<>();
                response.put("success", true);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
