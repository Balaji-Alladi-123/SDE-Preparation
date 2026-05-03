package Questions.ParkingLotDesign.Ticket;

import java.time.LocalDateTime;

public interface FeeCalculationStrategy {
    double calculateFee(LocalDateTime in, LocalDateTime out);
}
