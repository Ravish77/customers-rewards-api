package util;

public class RewardsCalculator {
	
	public static int calculatePoints(double amount) {
        int points = 0;

        if (amount > 100) {
            points += (int) ((amount - 100) * 2);
            points += 50; // 1 point per dollar between $50 and $100
        } else if (amount > 50) {
            points += (int) (amount - 50);
        }

        return points;
    }
	
}
