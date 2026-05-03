package Questions.MovieTicketBookingSystem.enums;

public enum SeatType {
    SILVER(100),
    GOLD(250),
    DIAMOND(350);

    private double price;
    SeatType(double price) {
        this.price = price;
    }
    public double getPrice() {
        return price;
    }
}
