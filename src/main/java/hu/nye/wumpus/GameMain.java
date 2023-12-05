package hu.nye.wumpus;

import hu.nye.wumpus.gameengine.GameManager;

import java.io.IOException;

public class GameMain {
    public static void main(String[] args) throws IOException {
        GameManager gameManager = new GameManager();
        gameManager.startGame();
    }
}
