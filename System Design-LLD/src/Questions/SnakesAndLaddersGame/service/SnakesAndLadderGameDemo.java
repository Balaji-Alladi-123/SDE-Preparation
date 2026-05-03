package Questions.SnakesAndLaddersGame.service;

import Questions.SnakesAndLaddersGame.model.Ladder;
import Questions.SnakesAndLaddersGame.model.Player;
import Questions.SnakesAndLaddersGame.model.Snake;

import java.util.ArrayList;
import java.util.List;

public class SnakesAndLadderGameDemo {

    private static SnakesAndLadderService snakesAndLadderService = SnakesAndLadderService.getInstance();

    public static void main(String[] args) {
        // Step 1: Initialize board with size and number of dice
        snakesAndLadderService.initializeBoardAndDice(100, 1); // board size 100, 1 dice

        // Step 2: Dummy ladders
        List<Ladder> laddersList = new ArrayList<>();
        laddersList.add(new Ladder(2, 23));
        laddersList.add(new Ladder(8, 34));
        laddersList.add(new Ladder(20, 77));

        // Step 3: Dummy snakes
        List<Snake> snakesList = new ArrayList<>();
        snakesList.add(new Snake(99, 5));
        snakesList.add(new Snake(70, 55));
        snakesList.add(new Snake(52, 42));

        // Step 4: Dummy players
        List<Player> playersList = new ArrayList<>();
        playersList.add(new Player("Alice"));
        playersList.add(new Player("Bob"));

        // Step 5: Set up the game
        snakesAndLadderService.addSnakesToBoard(snakesList);
        snakesAndLadderService.addLaddersToBoard(laddersList);
        snakesAndLadderService.addPlayersforBorad(playersList);

        System.out.println("\nStarting Game with Dummy Data...\n");
        snakesAndLadderService.startGame();
    }
}
