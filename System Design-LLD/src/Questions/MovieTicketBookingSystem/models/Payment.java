package Questions.MovieTicketBookingSystem.models;

import Questions.MovieTicketBookingSystem.enums.PaymentStatus;

import java.util.Random;

public class Payment {
    private final int id;
    private final double amount;
    private final PaymentStatus status;
    private final String transactionId;

    public Payment(double amount, PaymentStatus status, String transactionId) {
        Random random = new Random();
        this.id = random.nextInt(100);
        this.amount = amount;
        this.status = status;
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public String getTransactionId() {
        return transactionId;
    }
}
