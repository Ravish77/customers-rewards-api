package com.rewards.dto;

import java.util.Map;

public class RewardsResponseDto {
	
	
	private String customerId;
    private Map<String, Integer> monthlyRewards;
    private int totalRewards;

    public RewardsResponseDto(String customerId, Map<String, Integer> monthlyRewards, int totalRewards) {
        this.customerId = customerId;
        this.monthlyRewards = monthlyRewards;
        this.totalRewards = totalRewards;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Map<String, Integer> getMonthlyRewards() {
        return monthlyRewards;
    }

    public int getTotalRewards() {
        return totalRewards;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setMonthlyRewards(Map<String, Integer> monthlyRewards) {
        this.monthlyRewards = monthlyRewards;
    }

    public void setTotalRewards(int totalRewards) {
        this.totalRewards = totalRewards;
    }

}
