package com.exemplo.webappfinancemanager.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exemplo.webappfinancemanager.entity.Transaction;
import com.exemplo.webappfinancemanager.repository.TransactionRepository;
import com.exemplo.webappfinancemanager.repository.UserRepository;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUserName(username);
    }
    
    @Autowired
    private TransactionRepository transactionRepository;

    public Optional<Transaction> findById(String id) {
        return transactionRepository.findById(id);
    }

    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
