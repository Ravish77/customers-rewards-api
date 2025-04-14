package com.rewards.controller;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rewards.dto.RewardsResponseDto;
import com.rewards.service.RewardsService;

@RestController
@RequestMapping("/api/rewards")
public class RewardsController {
	
	@Autowired
    private RewardsService rewardsService;

    // 1. Get rewards for all customers
    @GetMapping("/all")
    public List<RewardsResponseDto> getAllCustomerRewards() {
        return rewardsService.getAllCustomerRewards();
    }

    // 2. Get rewards for a specific customer
    @GetMapping("/customer/{customerId}")
    public RewardsResponseDto getCustomerRewards(@PathVariable String customerId) {
        return rewardsService.getRewardsByCustomerId(customerId);
    }

    // 3. Get rewards for a customer for a specific time
    @GetMapping("/customer/{customerId}/rewards")
    public RewardsResponseDto getCustomerFilteredRewards(
            @PathVariable String customerId,
            @RequestParam(value = "month", required = false) @DateTimeFormat(pattern = "yyyy-MM") YearMonth month,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        if (month != null) {
            return rewardsService.getRewardsByCustomerAndMonth(customerId, month.getMonthValue());
        } else if (startDate != null && endDate != null) {
            return rewardsService.getRewardsByCustomerAndDateRange(customerId, startDate, endDate);
        } else {
            return rewardsService.getRewardsByCustomerId(customerId);
        }
    }

	
}
