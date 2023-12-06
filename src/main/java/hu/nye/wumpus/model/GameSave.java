package hu.nye.wumpus.model;

public class GameSave {

    private String playerName;
    private String gameData;
    private int playerScore;

    public GameSave(String playerName, String gameData, int playerScore) {
        this.playerName = playerName;
        this.gameData = gameData;
        this.playerScore = playerScore;
    }

    // Getterek és setterek

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getGameData() {
        return gameData;
    }

    public void setGameData(String gameData) {
        this.gameData = gameData;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
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