package controller;




import dto.RewardsResponseDto;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import service.RewardsService;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RewardsControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RewardsService rewardsService;

    @InjectMocks
    private RewardsController rewardsController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(rewardsController).build();
    }

    @Test
    public void testGetAllCustomerRewards() throws Exception {
        RewardsResponseDto dto = new RewardsResponseDto();
        when(rewardsService.getAllCustomerRewards()).thenReturn(Collections.singletonList(dto));

        mockMvc.perform(get("/api/rewards/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }

    @Test
    public void testGetCustomerRewards() throws Exception {
        String customerId = "123";
        RewardsResponseDto dto = new RewardsResponseDto();
        when(rewardsService.getRewardsByCustomerId(customerId)).thenReturn(dto);

        mockMvc.perform(get("/api/rewards/customer/{customerId}", customerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").exists());
    }

    @Test
    public void testGetCustomerMonthlyRewards() throws Exception {
        String customerId = "123";
        int month = 3;
        RewardsResponseDto dto = new RewardsResponseDto();
        when(rewardsService.getRewardsByCustomerAndMonth(customerId, month)).thenReturn(dto);

        mockMvc.perform(get("/api/rewards/customer/{customerId}/month", customerId)
                        .param("month", String.valueOf(month)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetCustomerRewardsInRange() throws Exception {
        String customerId = "123";
        LocalDate start = LocalDate.of(2024, 1, 1);
        LocalDate end = LocalDate.of(2024, 3, 31);
        RewardsResponseDto dto = new RewardsResponseDto();
        when(rewardsService.getRewardsByCustomerAndDateRange(customerId, start, end)).thenReturn(dto);

        mockMvc.perform(get("/api/rewards/customer/{customerId}/range", customerId)
                        .param("startDate", start.toString())
                        .param("endDate", end.toString()))
                .andExpect(status().isOk());
    }
}


