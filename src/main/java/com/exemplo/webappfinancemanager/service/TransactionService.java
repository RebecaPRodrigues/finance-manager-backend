package com.exemplo.webappfinancemanager.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemplo.webappfinancemanager.dto.RegisterTransactionDTO;
import com.exemplo.webappfinancemanager.dto.ViewTransactionDTO;
import com.exemplo.webappfinancemanager.entity.Transaction;
import com.exemplo.webappfinancemanager.entity.User;
import com.exemplo.webappfinancemanager.repository.TransactionRepository;
import com.exemplo.webappfinancemanager.repository.UserRepository;

@Service
public class TransactionService {
	
	@Autowired
    private TransactionRepository repository;
	
	@Autowired
	private UserRepository userRepository;

	public List<ViewTransactionDTO> findByUserId(String userId) {
		return repository.findByUserId(userId).stream().map(tran -> parseToDTO(tran)).collect(Collectors.toList());
	}

	public ViewTransactionDTO save(RegisterTransactionDTO dto) {
		
		Optional<User> userId = userRepository.findById(dto.userId());
		
		if(userId.isPresent()) {
			
			
			Transaction tran = Transaction.builder()
					.user(userId.get())
					.type(dto.type())
					.amount(dto.amount())
					.transactionWith(dto.transactionWith())
					.description(dto.description())
					.date(dto.date())
					.category(dto.category())
					.paymentMethod(dto.paymentMethod())
					.build();
			
			repository.save(tran);
			
			return parseToDTO(tran);
		} else {
			return null;
		}
		
		
	}

	public ViewTransactionDTO findById(String id) {
		
		Optional<Transaction> opt = repository.findById(id);
		
		if(opt.isPresent()) {
			return parseToDTO(opt.get());
		}
				
		return null;
	}
	
	  public void save(Transaction transaction) {
	        repository.save(transaction);
	    }

	public void delete(String transactionId) {
		repository.deleteById(transactionId);
		
	}
	
	private ViewTransactionDTO parseToDTO(Transaction tran) {
		return new ViewTransactionDTO(tran.getId(), tran.getType().name(), tran.getAmount(), tran.getTransactionWith(),
				tran.getDescription(), tran.getDate().toString(), tran.getCategory().name(), tran.getPaymentMethod());
	}

}
