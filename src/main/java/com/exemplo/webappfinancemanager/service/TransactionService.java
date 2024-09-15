package com.exemplo.webappfinancemanager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemplo.webappfinancemanager.dto.RegisterTransactionDTO;
import com.exemplo.webappfinancemanager.dto.ViewTransactionDTO;
import com.exemplo.webappfinancemanager.entity.Transaction;
import com.exemplo.webappfinancemanager.entity.User;
import com.exemplo.webappfinancemanager.repository.TransactionRepository;
import com.exemplo.webappfinancemanager.repository.UserRepository;


//@Service
//public class TransactionService {
//	
//	@Autowired
//    private TransactionRepository repository;
//	
//	@Autowired
//	private UserRepository userRepository;
//
//	public List<ViewTransactionDTO> findByUserId(String userId) {
//		return repository.findByUserId(userId).stream().map(tran -> parseToDTO(tran)).collect(Collectors.toList());
//	}
//
////	public ViewTransactionDTO save(RegisterTransactionDTO dto) {
////		
////		Optional<User> userId = userRepository.findById(dto.userId());
////		
////		 if(userId.isEmpty()) {
////		        throw new IllegalArgumentException("Usuário não encontrado");
////		    }
////			
////			Transaction tran = Transaction.builder()
////					.user(userId.get())
////					.type(dto.type())
////					.amount(dto.amount())
////					.transactionWith(dto.transactionWith())
////					.description(dto.description())
////					.date(dto.date())
////					.category(dto.category())
////					.paymentMethod(dto.paymentMethod())
////					.build();
////			
////			repository.save(tran);
////			
////			return parseToDTO(tran);
////		} /*
////			 * else { return null;
////			 */
////		
//	public ViewTransactionDTO save(RegisterTransactionDTO dto) {
//	    Optional<User> userOpt = userRepository.findById(dto.userId());
//	    if (userOpt.isEmpty()) {
//	        throw new IllegalArgumentException("Usuário não encontrado");
//	    }
//	    User user = userOpt.get();
//	    
//	    Transaction transaction = Transaction.builder()
//	        .user(user)
//	        .type(dto.type())
//	        .amount(dto.amount())
//	        .transactionWith(dto.transactionWith())
//	        .description(dto.description())
//	        .date(dto.date())
//	        .category(dto.category())
//	        .paymentMethod(dto.paymentMethod())
//	        .build();
//	    
//	    Transaction savedTransaction = repository.save(transaction);
//	    return parseToDTO(savedTransaction);
//	}
//
//
//	public ViewTransactionDTO findById(String id) {
//		
//		Optional<Transaction> opt = repository.findById(id);
//		
//		if(opt.isPresent()) {
//			return parseToDTO(opt.get());
//		}
//				
//		return null;
//	}
//	
//	  public void save(Transaction transaction) {
//	        repository.save(transaction);
//	    }
//
//	public void delete(String transactionId) {
//		repository.deleteById(transactionId);
//		
//	}
//	
//	private ViewTransactionDTO parseToDTO(Transaction tran) {
//		return new ViewTransactionDTO(tran.getId(), tran.getType().name(), tran.getAmount(), tran.getTransactionWith(),
//				tran.getDescription(), tran.getDate().toString(), tran.getCategory().name(), tran.getPaymentMethod());
//	}
//
//}

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private UserRepository userRepository;
    
    private static final Logger log = LoggerFactory.getLogger(TransactionService.class);

    
    public List<ViewTransactionDTO> findByUserId(String userId) {
        return repository.findByUserId(userId)
            .stream()
            .map(this::parseToDTO)
            .collect(Collectors.toList());
    }

    public ViewTransactionDTO save(RegisterTransactionDTO dto) {
        if (dto.userId() == null) {
            log.error("O userId está nulo, não é possível salvar a transação.");
            throw new IllegalArgumentException("O userId não pode ser nulo");
        }
    	
		log.info("Salvando transação para o userId: {}", dto.userId());

    	
        User user = userRepository.findById(dto.userId())
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        Transaction transaction = Transaction.builder()
            .user(user)
            .type(dto.type())
            .amount(dto.amount())
            .transactionWith(dto.transactionWith())
            .description(dto.description())
            .date(dto.date())
            .category(dto.category())
            .paymentMethod(dto.paymentMethod())
            .build();

        Transaction savedTransaction = repository.save(transaction);
        log.info("Transação salva com ID: {}", savedTransaction.getId());
        return parseToDTO(savedTransaction);
    }

    public ViewTransactionDTO findById(String id) {
        return repository.findById(id)
            .map(this::parseToDTO)
            .orElseThrow(() -> new IllegalArgumentException("Transação não encontrada"));
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

