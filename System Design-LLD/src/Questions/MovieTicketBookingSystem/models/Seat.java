package Questions.MovieTicketBookingSystem.models;

import Questions.MovieTicketBookingSystem.enums.SeatStatus;
import Questions.MovieTicketBookingSystem.enums.SeatType;

public class Seat {
    int id;
    int row;
    int column;
    SeatType seatType;
    SeatStatus status;

    public Seat(int id, int row, int column, SeatType seatType, SeatStatus status) {
        this.id = id;
        this.row = row;
        this.column = column;
        this.seatType = seatType;
        this.status = SeatStatus.AVAILABLE;
    }

    public int getId() {
        return id;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }
}
