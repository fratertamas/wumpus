package hu.nye.wumpus.io.saver;

import java.io.IOException;
import javax.xml.stream.XMLStreamException;

import hu.nye.wumpus.model.Player;

public interface GameSaver {
    void saveGame(Player player, String save, int playerScore) throws IOException, XMLStreamException;
}
