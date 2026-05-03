package Questions.MovieTicketBookingSystem.StrategyPattern.Pricing;

import Questions.MovieTicketBookingSystem.models.Seat;

import java.util.List;

public interface PricingStrategy {
    double calculatePrice(List<Seat> seats);
}
