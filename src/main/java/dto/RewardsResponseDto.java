package dto;


import java.util.List;
import java.util.Map;

import model.Transaction;

public class RewardsResponseDto {

    private String customerId;
    private List<Transaction> transactions;
    private Map<String, Integer> monthlyRewards;
    private int totalRewards;

    // Constructors
    public RewardsResponseDto() {
    }

    public RewardsResponseDto(String customerId, List<Transaction> transactions, Map<String, Integer> monthlyRewards, int totalRewards) {
        this.customerId = customerId;
        this.transactions = transactions;
        this.monthlyRewards = monthlyRewards;
        this.totalRewards = totalRewards;
    }

    // Getters and setters

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Map<String, Integer> getMonthlyRewards() {
        return monthlyRewards;
    }

    public void setMonthlyRewards(Map<String, Integer> monthlyRewards) {
        this.monthlyRewards = monthlyRewards;
    }

    public int getTotalRewards() {
        return totalRewards;
    }

    public void setTotalRewards(int totalRewards) {
        this.totalRewards = totalRewards;
    }
}
