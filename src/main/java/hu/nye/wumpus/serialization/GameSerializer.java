package hu.nye.wumpus.serialization;

import java.io.IOException;

import hu.nye.wumpus.model.Player;

import javax.xml.stream.XMLStreamException;

public interface GameSerializer {
    void saveGame(Player player, String save, int playerScore) throws IOException, XMLStreamException;
}
