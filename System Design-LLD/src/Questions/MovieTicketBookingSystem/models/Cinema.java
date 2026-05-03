package Questions.MovieTicketBookingSystem.models;

import java.util.List;

public class Cinema {
    private final int id;
    private String name;
    private List<Screen> screenList;
    private City city;

    public Cinema(int id, String name, List<Screen> screenList, City city) {
        this.id = id;
        this.name = name;
        this.screenList = screenList;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Screen> getScreenList() {
        return screenList;
    }

    public City getCity() {
        return city;
    }
}
