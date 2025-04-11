package controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dto.RewardsResponseDto;
import service.RewardsService;

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

    // 3. Get rewards for a customer for a specific month
    @GetMapping("/customer/{customerId}/month")
    public RewardsResponseDto getCustomerMonthlyRewards(
            @PathVariable String customerId,
            @RequestParam("month") int month
    ) {
        return rewardsService.getRewardsByCustomerAndMonth(customerId, month);
    }


    // 4. Get rewards for a customer between a date range
    @GetMapping("/customer/{customerId}/range")
    public RewardsResponseDto getCustomerRewardsInRange(
            @PathVariable String customerId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return rewardsService.getRewardsByCustomerAndDateRange(customerId, startDate, endDate);
    }
	
}
