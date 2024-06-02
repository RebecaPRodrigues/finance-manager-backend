package com.exemplo.webappfinancemanager.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transactions")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class Transaction {
	
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private TransactionType type;
    private Double amount;
    private String transactionWith;
    private String description;
    private LocalDate date;
    private TransactionCategory category;
    private String paymentMethod;
    
    
    
	public void setId(String id) {
		this.id = id;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setType(TransactionType type) {
		this.type = type;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public void setTransactionWith(String transactionWith) {
		this.transactionWith = transactionWith;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public void setCategory(TransactionCategory category) {
		this.category = category;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
    
	
    
 
    
}
