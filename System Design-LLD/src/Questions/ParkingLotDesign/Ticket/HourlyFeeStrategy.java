package Questions.ParkingLotDesign.Ticket;

import java.time.LocalDateTime;
import java.util.Date;

public class HourlyFeeStrategy implements FeeCalculationStrategy {
    private final double hourlyFees = 100;
    @Override
    public double calculateFee(LocalDateTime in, LocalDateTime out) {
        long hours = java.time.Duration.between(in, out).toHours();
        return Math.max(1, hours) * hourlyFees;
    }
}
