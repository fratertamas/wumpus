package hu.nye.wumpus.model;

/**
 * GameSave.
 * Mentésre készült adatgyűjtő
 */
public class GameSave {
    private String playerName;
    private String gameData;
    private int playerScore;

    public GameSave(String playerName, String gameData, int playerScore) {
        this.playerName = playerName;
        this.gameData = gameData;
        this.playerScore = playerScore;
    }



    @Override
    public String toString() {
        return "GameSave{" +
                "playerName='" + playerName + '\'' +
                ", gameData='" + gameData + '\'' +
                ", playerScore=" + playerScore +
                '}';
    }
}