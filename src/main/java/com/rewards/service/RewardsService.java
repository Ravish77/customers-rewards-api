package com.rewards.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rewards.dto.RewardsResponseDto;
import com.rewards.model.Transaction;
import com.rewards.repository.RewardsRepository;
import com.rewards.util.RewardsCalculator;

@Service
public class RewardsService {
	
	@Autowired
    private RewardsRepository rewardsRepository;

    public List<RewardsResponseDto> getAllCustomerRewards() {
        List<Transaction> transactions = rewardsRepository.findAll();

        // Group by customerId
        Map<String, List<Transaction>> groupedByCustomer = new HashMap<>();

        for (Transaction txn : transactions) {
            String customerId = txn.getCustomerId();

            if (!groupedByCustomer.containsKey(customerId)) {
                groupedByCustomer.put(customerId, new ArrayList<>());
            }

            groupedByCustomer.get(customerId).add(txn);
        }


        List<RewardsResponseDto> rewardResponses = new ArrayList<>();

        for (Map.Entry<String, List<Transaction>> entry : groupedByCustomer.entrySet()) {
            String customerId = entry.getKey();
            List<Transaction> customerTxns = entry.getValue();

            Map<String, Integer> monthlyRewards = new HashMap<>();
            int totalRewards = 0;

            for (Transaction txn : customerTxns) {
                int points = RewardsCalculator.calculatePoints(txn.getAmount());
                String month = txn.getDate().getMonth().toString(); // e.g., "JANUARY"

                monthlyRewards.put(month,
                        monthlyRewards.getOrDefault(month, 0) + points);

                totalRewards += points;
            }

            rewardResponses.add(new RewardsResponseDto(customerId, monthlyRewards, totalRewards));
        }

        return rewardResponses;
    }

}
