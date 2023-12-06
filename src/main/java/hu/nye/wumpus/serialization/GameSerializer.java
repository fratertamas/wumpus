package hu.nye.wumpus.serialization;

import java.io.IOException;
import javax.xml.stream.XMLStreamException;

import hu.nye.wumpus.model.Player;

public interface GameSerializer {
    void saveGame(Player player, String save, int playerScore) throws IOException, XMLStreamException;
}
