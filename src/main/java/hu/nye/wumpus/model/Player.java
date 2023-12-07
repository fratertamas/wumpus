package hu.nye.wumpus.model;

/**
 * Player.
 * Játékos objektum
 */
public class Player {
    private final String playerName;

    public Player(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }
}
