package Questions.MovieTicketBookingSystem.service;

import Questions.MovieTicketBookingSystem.StrategyPattern.Payment.PaymentStrategy;
import Questions.MovieTicketBookingSystem.concurrencyImp.SeatLockManager;
import Questions.MovieTicketBookingSystem.enums.PaymentStatus;
import Questions.MovieTicketBookingSystem.models.*;

import java.util.List;
import java.util.Optional;

public class BookingManager {
    private final SeatLockManager seatLockManager;

    BookingManager(SeatLockManager seatLockManager) {
        this.seatLockManager = seatLockManager;
    }

    public Optional<Booking> createBooking(User user, Show show, List<Seat> seats, PaymentStrategy paymentStrategy) {
        seatLockManager.lockSeats(show, seats, user.getId());

        double totalAmount = show.getPricingStrategy().calculatePrice(seats);
        Payment payment = paymentStrategy.payAmount(totalAmount);

        if (payment.getStatus() == PaymentStatus.SUCCESS) {
            Booking booking = new Booking.BookingBuilder()
                    .setUser(user)
                    .setPayment(payment)
                    .setSeats(seats)
                    .setTotalAmount(totalAmount)
                    .build();
            booking.confirmBooking();
            seatLockManager.unLockSeat(show, seats, user.getId());
            return Optional.of(booking);
        } else {
            System.out.println("Payment failed. Please try again.");
            return Optional.empty();
        }
    }
}
