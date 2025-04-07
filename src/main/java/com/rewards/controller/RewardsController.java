package com.rewards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rewards.dto.RewardsResponseDto;
import com.rewards.service.RewardsService;

@RestController
@RequestMapping("/api/rewards")
public class RewardsController {
	
	
	@Autowired
    private RewardsService rewardService;

    @GetMapping
    public List<RewardsResponseDto> getAllCustomerRewards() {
        return rewardService.getAllCustomerRewards();
    }
	
}
