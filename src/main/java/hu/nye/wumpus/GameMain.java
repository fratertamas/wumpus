package hu.nye.wumpus;

import java.io.IOException;
import java.sql.SQLException;

import hu.nye.wumpus.gameengine.GameManager;

public class GameMain {
    public static void main(String[] args) throws IOException, SQLException {
        GameManager gameManager = new GameManager();
        gameManager.startGame();
    }
}
