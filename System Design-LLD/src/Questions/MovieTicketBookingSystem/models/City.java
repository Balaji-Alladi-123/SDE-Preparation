package Questions.MovieTicketBookingSystem.models;

import java.util.Random;

public class City {
     int id;
    String name;

    public City(String name) {
        Random random = new Random();
        this.id = random.nextInt(100);
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
