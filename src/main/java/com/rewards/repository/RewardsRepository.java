package com.rewards.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rewards.model.Transaction;

@Repository
public interface RewardsRepository extends JpaRepository<Transaction, Long>{
	
	List<Transaction> findByCustomerId(String customerId);

    List<Transaction> findByCustomerIdAndDateBetween(String customerId, LocalDate startDate, LocalDate endDate);
	
}
