package hu.nye.wumpus.serialization;

import hu.nye.wumpus.model.Player;

import java.io.IOException;

public interface GameSerializer {
    void saveGame(Player player, String save, int playerScore) throws IOException;
}
