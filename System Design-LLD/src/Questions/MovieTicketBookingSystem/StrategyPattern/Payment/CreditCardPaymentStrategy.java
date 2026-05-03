package Questions.MovieTicketBookingSystem.StrategyPattern.Payment;

import Questions.MovieTicketBookingSystem.enums.PaymentStatus;
import Questions.MovieTicketBookingSystem.models.Payment;

import java.util.UUID;

public class CreditCardPaymentStrategy implements PaymentStrategy{
    private final String  cardNumber;
    private final String cvvNumber;

   CreditCardPaymentStrategy(String cardNumber, String cvvNumber) {
       this.cardNumber = cardNumber;
       this.cvvNumber = cvvNumber;
   }

    @Override
    public Payment payAmount(double amount) {
        System.out.printf("Processing credit card payment of $%.2f%n", amount);
        // Simulate payment gateway interaction
        boolean paymentSuccess = Math.random() > 0.05; // 95% success rate
        return new Payment(amount,paymentSuccess? PaymentStatus.SUCCESS:PaymentStatus.FAILURE,"TXN_"+ UUID.randomUUID());
    }
}
