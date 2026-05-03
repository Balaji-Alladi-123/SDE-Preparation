package Questions.MovieTicketBookingSystem.StrategyPattern.Payment;

import Questions.MovieTicketBookingSystem.enums.PaymentStatus;
import Questions.MovieTicketBookingSystem.models.Payment;

import java.util.UUID;

public class PayPalPaymentStrategy implements PaymentStrategy{
    private String email;
    private String password;

    public PayPalPaymentStrategy(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public Payment payAmount(double amount) {
        System.out.printf("Processing PayPal payment of $%.2f%n", amount);
        // Simulate payment gateway interaction
        boolean paymentSuccess = Math.random() > 0.05; // 95% success rate
        return new Payment(amount,paymentSuccess? PaymentStatus.SUCCESS:PaymentStatus.FAILURE,"TXN_"+ UUID.randomUUID());
    }
}
