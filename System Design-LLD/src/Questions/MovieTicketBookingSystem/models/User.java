package Questions.MovieTicketBookingSystem.models;

import java.util.Random;

public class User {
    int id;
    String name;
    String email;
    String phNumber;

    User(String name, String email, String phNumber) {
        Random random = new Random();
        this.id = random.nextInt(100);
        this.name = name;
        this.email = email;
        this.phNumber = phNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhNumber() {
        return phNumber;
    }
}
