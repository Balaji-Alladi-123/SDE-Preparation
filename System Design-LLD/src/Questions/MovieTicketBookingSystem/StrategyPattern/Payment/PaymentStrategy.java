package Questions.MovieTicketBookingSystem.StrategyPattern.Payment;

import Questions.MovieTicketBookingSystem.models.Payment;

public interface PaymentStrategy {
    Payment payAmount(double amount);
}
