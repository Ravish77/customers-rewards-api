package util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RewardsCalculatorTest {

    @Test
    void testPointsBelow50() {
        assertEquals(0, RewardsCalculator.calculatePoints(45));
    }

    @Test
    void testPointsBetween50And100() {
        assertEquals(20, RewardsCalculator.calculatePoints(70)); // (70 - 50) = 20
    }

    @Test
    void testPointsAbove100() {
        assertEquals(90, RewardsCalculator.calculatePoints(120)); // 2*(120-100) + (100-50) = 40 + 50 = 90
    }

    @Test
    void testPointsExactly100() {
        assertEquals(50, RewardsCalculator.calculatePoints(100)); // 100-50 = 50
    }
}
