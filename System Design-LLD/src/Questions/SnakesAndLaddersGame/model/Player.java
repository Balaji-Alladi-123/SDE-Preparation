package Questions.SnakesAndLaddersGame.model;

import java.util.Random;
import java.util.UUID;

public class Player {
    private int id;
    private String name;

    public Player(String name) {
        this.id = generateRandomId();
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int generateRandomId() {
        Random random = new Random();
        int id = 100000 + random.nextInt(900000);
        return id;
    }
}
