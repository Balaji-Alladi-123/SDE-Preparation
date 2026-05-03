package Questions.MovieTicketBookingSystem.StrategyPattern.Pricing;

import Questions.MovieTicketBookingSystem.models.Seat;

import java.util.List;

public class BenifitAndFirstDayPricingStrategy implements PricingStrategy{
    double extraPrice = 1.5;
    @Override
    public double calculatePrice(List<Seat> seats) {
        double basePrice = seats.stream().mapToDouble(seat -> seat.getSeatType().getPrice()).sum();
        return basePrice * extraPrice;
    }
}
