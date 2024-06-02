package com.exemplo.webappfinancemanager.config;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.exemplo.webappfinancemanager.entity.Transaction;
import com.exemplo.webappfinancemanager.entity.TransactionCategory;
import com.exemplo.webappfinancemanager.entity.TransactionType;
import com.exemplo.webappfinancemanager.entity.User;
import com.exemplo.webappfinancemanager.entity.UserRole;
import com.exemplo.webappfinancemanager.repository.TransactionRepository;
import com.exemplo.webappfinancemanager.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TransactionRepository tranRepository;
	
	
	@Override
	public void run(String... args) throws Exception {

		
		// ------------------------------------------------      users
		String encryptedPassword = new BCryptPasswordEncoder().encode("12345678");
		
		User user1 = User.builder()
				.userName("rebeca")
				.email("rebecarodrigues@gmail.com")
				.password(encryptedPassword)
				.role(UserRole.ADMIN).build();
		
		userRepository.save(user1);
		
		// ------------------------------------------------  transactions
		
		
		Transaction tran1 = Transaction.builder()
				.user(user1)
				.type(TransactionType.RECEITA)
				.amount(500.5d) // d pra saber que é DOuble
				.transactionWith("some-store")
				.description("some-description")
				.date(LocalDate.of(2024, 5, 1))
				.category(TransactionCategory.SALARIO)
				.paymentMethod("credit card")
				.build();
		
		tranRepository.save(tran1);		
		
	}

}
