package service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.RewardsResponseDto;
import model.Transaction;
import repository.RewardsRepository;
import util.RewardsCalculator;

@Service
public class RewardsService {
	

    @Autowired
    private RewardsRepository rewardsRepository;

    // 1. Total and monthly rewards for all customers
    public List<RewardsResponseDto> getAllCustomerRewards() {
        List<Transaction> transactions = rewardsRepository.findAll();
        return processRewards(transactions);
    }

    // 2. Rewards for a specific customer
    public RewardsResponseDto getRewardsByCustomerId(String customerId) {
        List<Transaction> all = rewardsRepository.findAll();
        List<Transaction> filtered = new ArrayList<>();

        for (Transaction t : all) {
            if (t.getCustomerId().equals(customerId)) {
                filtered.add(t);
            }
        }

        List<RewardsResponseDto> result = processRewards(filtered);
        return result.isEmpty() ? null : result.get(0);
    }

    // 3. Rewards for a specific customer in a specific month
    public RewardsResponseDto getRewardsByCustomerAndMonth(String customerId, int month) {
        List<Transaction> all = rewardsRepository.findAll();
        List<Transaction> filtered = new ArrayList<>();

        for (Transaction t : all) {
            if (t.getCustomerId().equals(customerId)
                && t.getDate().getMonthValue() == month) {
                filtered.add(t);
            }
        }

        List<RewardsResponseDto> result = processRewards(filtered);
        return result.isEmpty() ? null : result.get(0);
    }

    // 4. Rewards for a customer for a given date range
    public RewardsResponseDto getRewardsByCustomerAndDateRange(String customerId, LocalDate start, LocalDate end) {
        List<Transaction> all = rewardsRepository.findAll();
        List<Transaction> filtered = new ArrayList<>();

        for (Transaction t : all) {
            LocalDate date = t.getDate();
            if (t.getCustomerId().equals(customerId)
                && (date.isEqual(start) || date.isAfter(start))
                && (date.isEqual(end) || date.isBefore(end))) {
                filtered.add(t);
            }
        }

        List<RewardsResponseDto> result = processRewards(filtered);
        return result.isEmpty() ? null : result.get(0);
    }

    private List<RewardsResponseDto> processRewards(List<Transaction> transactions) {
        Map<String, Map<Month, Integer>> customerMonthPointsMap = new HashMap<>();

        for (Transaction tx : transactions) {
            String customerId = tx.getCustomerId();
            Month month = tx.getDate().getMonth();
            int points = RewardsCalculator.calculatePoints(tx.getAmount());

            if (!customerMonthPointsMap.containsKey(customerId)) {
                customerMonthPointsMap.put(customerId, new HashMap<Month, Integer>());
            }

            Map<Month, Integer> monthPoints = customerMonthPointsMap.get(customerId);
            if (!monthPoints.containsKey(month)) {
                monthPoints.put(month, points);
            } else {
                monthPoints.put(month, monthPoints.get(month) + points);
            }
        }

        List<RewardsResponseDto> result = new ArrayList<>();

        for (String customerId : customerMonthPointsMap.keySet()) {
            Map<Month, Integer> monthlyPoints = customerMonthPointsMap.get(customerId);
            Map<String, Integer> monthlyRewardsStrKey = new HashMap<>();

            for (Map.Entry<Month, Integer> entry : monthlyPoints.entrySet()) {
                monthlyRewardsStrKey.put(entry.getKey().toString(), entry.getValue());
            }

            int totalRewards = 0;
            for (Integer points : monthlyPoints.values()) {
                totalRewards += points;
            }

            List<Transaction> customerTxns = new ArrayList<>();
            for (Transaction tx : transactions) {
                if (tx.getCustomerId().equals(customerId)) {
                    customerTxns.add(tx);
                }
            }

            RewardsResponseDto dto = new RewardsResponseDto(customerId, customerTxns, monthlyRewardsStrKey, totalRewards);
            result.add(dto);
        }

        return result;
    }

}
