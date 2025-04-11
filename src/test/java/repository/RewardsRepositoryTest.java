package repository;

import model.Transaction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RewardsRepositoryTest {

    @Autowired
    private RewardsRepository rewardsRepository;

    @Test
    @DisplayName("Should find transactions by customer ID")
    void testFindByCustomerId() {
        Transaction tx1 = new Transaction("C001", 120, LocalDate.of(2024, 3, 10));
        Transaction tx2 = new Transaction("C001", 80, LocalDate.of(2024, 3, 15));
        Transaction tx3 = new Transaction("C002", 100, LocalDate.of(2024, 3, 20));
        rewardsRepository.saveAll(List.of(tx1, tx2, tx3));

        List<Transaction> result = rewardsRepository.findByCustomerId("C001");

        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(t -> t.getCustomerId().equals("C001")));
    }

    @Test
    @DisplayName("Should find transactions by customer ID and date range")
    void testFindByCustomerIdAndDateBetween() {
        Transaction tx1 = new Transaction("C001", 100, LocalDate.of(2024, 2, 5));
        Transaction tx2 = new Transaction("C001", 150, LocalDate.of(2024, 3, 10));
        Transaction tx3 = new Transaction("C001", 90, LocalDate.of(2024, 3, 25));
        rewardsRepository.saveAll(List.of(tx1, tx2, tx3));

        LocalDate start = LocalDate.of(2024, 3, 1);
        LocalDate end = LocalDate.of(2024, 3, 31);

        List<Transaction> result = rewardsRepository.findByCustomerIdAndDateBetween("C001", start, end);

        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(t -> t.getDate().getMonthValue() == 3));
    }

    @Test
    @DisplayName("Should find transactions in a specific date range")
    void testFindByDateBetween() {
        Transaction tx1 = new Transaction("C001", 110, LocalDate.of(2024, 2, 15));
        Transaction tx2 = new Transaction("C002", 130, LocalDate.of(2024, 3, 5));
        Transaction tx3 = new Transaction("C003", 140, LocalDate.of(2024, 4, 1));
        rewardsRepository.saveAll(List.of(tx1, tx2, tx3));

        LocalDate start = LocalDate.of(2024, 3, 1);
        LocalDate end = LocalDate.of(2024, 3, 31);

        List<Transaction> result = rewardsRepository.findByDateBetween(start, end);

        assertEquals(1, result.size());
        assertEquals("C002", result.get(0).getCustomerId());
    }
}
