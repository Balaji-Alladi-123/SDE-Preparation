package Questions.MovieTicketBookingSystem.models;

import java.util.ArrayList;
import java.util.List;

public class Screen {
    private final int id;
    private List<Seat> seatList;
    Screen(int id) {
        this.id = id;
        this.seatList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public List<Seat> getSeatList() {
        return seatList;
    }
}
