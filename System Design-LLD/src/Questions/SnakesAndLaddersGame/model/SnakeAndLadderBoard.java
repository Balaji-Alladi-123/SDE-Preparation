package Questions.SnakesAndLaddersGame.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SnakeAndLadderBoard {

    private List<Snake> snakes;
    private List<Ladder> ladders;
    private int size;
    private Map<Integer, Integer> playerPieces;

    public SnakeAndLadderBoard(int size) {
       this.snakes = new ArrayList<>();
       this.ladders = new ArrayList<>();
       this.size = size;
       this.playerPieces = new HashMap<>();
    }

    public int getSize() {
        return size;
    }

    public List<Snake> getSnakes() {
        return snakes;
    }

    public List<Ladder> getLadders() {
        return ladders;
    }

    public Map<Integer, Integer> getPlayerPieces() {
        return playerPieces;
    }

    public void setSnakes(List<Snake> snakes) {
        this.snakes = snakes;
    }

    public void setLadders(List<Ladder> ladders) {
        this.ladders = ladders;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setPlayerPieces(Map<Integer, Integer> playerPieces) {
        this.playerPieces = playerPieces;
    }
}
