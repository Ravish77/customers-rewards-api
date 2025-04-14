package com.rewards.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rewards.model.Transaction;

@Repository
public interface RewardsRepository extends JpaRepository<Transaction, Long>{
	
	// Get all transactions for a specific customer
    List<Transaction> findByCustomerId(String customerId);

    // Get all transactions for a customer in a specific month
    List<Transaction> findByCustomerIdAndDateBetween(String customerId, LocalDate startDate, LocalDate endDate);

    // Get all transactions in a specific date range
    List<Transaction> findByDateBetween(LocalDate startDate, LocalDate endDate);
	
}
