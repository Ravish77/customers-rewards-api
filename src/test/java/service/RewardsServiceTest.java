package service;


import dto.RewardsResponseDto;
import model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.RewardsRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RewardsServiceTest {

    @Mock
    private RewardsRepository rewardsRepository;

    @InjectMocks
    private RewardsService rewardsService;

    private List<Transaction> transactions;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        transactions = Arrays.asList(
                new Transaction("C001", 120, LocalDate.of(2024, 3, 5)),
                new Transaction("C001", 75, LocalDate.of(2024, 3, 15)),
                new Transaction("C002", 90, LocalDate.of(2024, 2, 10))
        );
    }

    @Test
    void testGetAllCustomerRewards() {
        when(rewardsRepository.findAll()).thenReturn(transactions);
        List<RewardsResponseDto> result = rewardsService.getAllCustomerRewards();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(rewardsRepository, times(1)).findAll();
    }

    @Test
    void testGetRewardsByCustomerId() {
        when(rewardsRepository.findAll()).thenReturn(transactions);
        RewardsResponseDto result = rewardsService.getRewardsByCustomerId("C001");

        assertNotNull(result);
        assertEquals("C001", result.getCustomerId());
        assertEquals(2, result.getTransactions().size());
    }

    @Test
    void testGetRewardsByCustomerAndMonth() {
        when(rewardsRepository.findAll()).thenReturn(transactions);
        RewardsResponseDto result = rewardsService.getRewardsByCustomerAndMonth("C001", 3);

        assertNotNull(result);
        assertEquals("C001", result.getCustomerId());
        assertEquals(2, result.getTransactions().size());
    }

    @Test
    void testGetRewardsByCustomerAndDateRange() {
        LocalDate start = LocalDate.of(2024, 3, 1);
        LocalDate end = LocalDate.of(2024, 3, 31);

        when(rewardsRepository.findAll()).thenReturn(transactions);
        RewardsResponseDto result = rewardsService.getRewardsByCustomerAndDateRange("C001", start, end);

        assertNotNull(result);
        assertEquals("C001", result.getCustomerId());
        assertEquals(2, result.getTransactions().size());
    }

    @Test
    void testGetRewardsByCustomerId_NotFound() {
        when(rewardsRepository.findAll()).thenReturn(transactions);
        RewardsResponseDto result = rewardsService.getRewardsByCustomerId("C999");

        assertNull(result);
    }

}
