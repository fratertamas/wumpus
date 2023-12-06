package hu.nye.wumpus;

import java.io.IOException;
import java.sql.SQLException;
import javax.xml.stream.XMLStreamException;

import hu.nye.wumpus.gameengine.GameManager;

public class GameMain {
    public static void main(String[] args) throws IOException, SQLException, XMLStreamException {
        GameManager gameManager = new GameManager();
        gameManager.startGame();
    }
}
