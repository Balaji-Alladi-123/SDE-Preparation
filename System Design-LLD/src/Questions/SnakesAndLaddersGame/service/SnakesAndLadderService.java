package Questions.SnakesAndLaddersGame.service;

import Questions.SnakesAndLaddersGame.model.Ladder;
import Questions.SnakesAndLaddersGame.model.Player;
import Questions.SnakesAndLaddersGame.model.Snake;
import Questions.SnakesAndLaddersGame.model.SnakeAndLadderBoard;

import java.util.*;

public class SnakesAndLadderService {
    private static SnakesAndLadderService instance;
    private int boardSize;
    private int initializeNoOfPlayers;
    private Queue<Player> playerQueue;
    private boolean isGameCompleted;
    private int noOfDices;
    private boolean shouldGameContinueTillLastPlayer;
    private boolean shouldAllowMultipleDiceRollOnSix;
    private SnakeAndLadderBoard snakeAndLadderBoard;

    private SnakesAndLadderService() {
        this.playerQueue = new LinkedList<>();
    }

    public static synchronized SnakesAndLadderService getInstance() {
        if (instance == null) {
            instance = new SnakesAndLadderService();
        }
        return instance;
    }

    public void initializeBoardAndDice(int boardSize, int dice) {
        this.noOfDices = dice;
        this.boardSize = boardSize;
        this.snakeAndLadderBoard = new SnakeAndLadderBoard(boardSize);
    }

    public void addPlayersforBorad(List<Player> playersList) {
        if (playersList == null) return;
        this.initializeNoOfPlayers = playersList.size();
        Map<Integer, Integer> map = new HashMap<>();
        for (Player player : playersList) {
            map.putIfAbsent(player.getId(), 0);
            playerQueue.add(player);
        }
        snakeAndLadderBoard.setPlayerPieces(map);
    }

    public void setShouldGameContinueTillLastPlayer(boolean shouldGameContinueTillLastPlayer) {
        this.shouldGameContinueTillLastPlayer = shouldGameContinueTillLastPlayer;
    }

    public void setShouldAllowMultipleDiceRollOnSix(boolean shouldAllowMultipleDiceRollOnSix) {
        this.shouldAllowMultipleDiceRollOnSix = shouldAllowMultipleDiceRollOnSix;
    }

    public void addSnakesToBoard(List<Snake> snakes) {
        snakeAndLadderBoard.setSnakes(snakes);
    }

    public void addLaddersToBoard(List<Ladder> ladders) {
        snakeAndLadderBoard.setLadders(ladders);
    }

    private int getTotalValueAfterDiceRoll() {
        int diceNumber = DiceService.roll();
        int totalNumber = diceNumber;
        int count = 0;
        while (diceNumber == 6 && shouldAllowMultipleDiceRollOnSix) {
            if (++count == 3) {
                return 0;
            }
            diceNumber = DiceService.roll();
            totalNumber += diceNumber;
        }
        return totalNumber;
    }

    private boolean hasPlayerWon(Player player) {
        int playerPosition = snakeAndLadderBoard.getPlayerPieces().get(player.getId());
        return playerPosition == snakeAndLadderBoard.getSize();
    }

    private boolean isGameCompleted() {
        return playerQueue.isEmpty();
    }

    public void startGame() {
        while (!isGameCompleted()) {
            Player currentPlayer = playerQueue.poll();
            int diceValue = getTotalValueAfterDiceRoll();
            System.out.println(currentPlayer.getName() + " rolled: " + diceValue);
            movePlayer(currentPlayer, diceValue);
            int currentPos = snakeAndLadderBoard.getPlayerPieces().get(currentPlayer.getId());
            System.out.println(currentPlayer.getName() + " moved to position " + currentPos);

            if (hasPlayerWon(currentPlayer)) {
                System.out.println(currentPlayer.getName() + " has won the game!");
                snakeAndLadderBoard.getPlayerPieces().remove(currentPlayer.getId());
            } else {
                playerQueue.add(currentPlayer);
            }
        }
        System.out.println("Game Over!");
    }

    private void movePlayer(Player currentPlayer, int diceValue) {
        int oldPosition = snakeAndLadderBoard.getPlayerPieces().get(currentPlayer.getId());
        int newPosition = oldPosition + diceValue;

        if (newPosition > snakeAndLadderBoard.getSize()) {
            newPosition = oldPosition;
        } else {
            newPosition = getPositionAfterGoingThroughSnakeAndLadder(newPosition);
        }
        snakeAndLadderBoard.getPlayerPieces().put(currentPlayer.getId(), newPosition);
    }

    private int getPositionAfterGoingThroughSnakeAndLadder(int newPosition) {
        int previousPosition;
        do {
            previousPosition = newPosition;
            for (Snake snake : snakeAndLadderBoard.getSnakes()) {
                if (snake.getHead() == newPosition) {
                    System.out.println("Oops! Bitten by snake at " + newPosition);
                    newPosition = snake.getTail();
                }
            }
            for (Ladder ladder : snakeAndLadderBoard.getLadders()) {
                if (ladder.getStart() == newPosition) {
                    System.out.println("Yay! Climbed ladder from " + newPosition);
                    newPosition = ladder.getEnd();
                }
            }
        } while (newPosition != previousPosition);
        return newPosition;
    }
}
