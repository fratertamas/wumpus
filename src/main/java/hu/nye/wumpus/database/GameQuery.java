package hu.nye.wumpus.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.xml.stream.XMLStreamException;

import hu.nye.wumpus.gameengine.Game;
import hu.nye.wumpus.io.loader.impl.BoardDBLoader;
import hu.nye.wumpus.io.loader.impl.HeroDBLoader;
import hu.nye.wumpus.model.Player;

/**
 * Játék mentés, mentett játékok listázása, betöltés adatbázisból.
 * GameQuery
 */
public class GameQuery {

    /**
     * saveGame.
     * Játék mentése adatbázisba
     */
    public void saveGame(Player player, String save, int playerScore) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();

        String query = "INSERT INTO games (player, game, score) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, player.getPlayerName());
        preparedStatement.setString(2, save);
        preparedStatement.setInt(3, playerScore);

        preparedStatement.executeUpdate();

        connection.close();
    }

    /**
     * listSavedGames.
     * Adatbűzisba mentett játékállások listázása
     */
    public void listSavedGames() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();

        String query = "SELECT gameid, player, score  FROM games";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int gameId = resultSet.getInt("gameid");
            String player = resultSet.getString("player");
            int score = resultSet.getInt("score");

            System.out.println("GameID: " + gameId + "Player: " + player + ", Score: " + score);
        }

        resultSet.close();
        preparedStatement.close();

        connection.close();
    }

    /**
     * loadGame.
     * Játékállás betöltése adatbázisból
     */
    public void loadGame(String gameId) throws SQLException, XMLStreamException, IOException {
        Connection connection = DatabaseConnection.getConnection();

        String query = "SELECT player, game, score FROM games WHERE gameid = " + gameId + ";";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String player = resultSet.getString("player");
            String board = resultSet.getString("game");
            int score = resultSet.getInt("score");

            BoardDBLoader boardDBLoader = new BoardDBLoader();
            HeroDBLoader heroDBLoader = new HeroDBLoader();

            Game game = new Game(boardDBLoader.load(board), heroDBLoader.load(board), new Player(player), score);
            game.playGame();
        }

        resultSet.close();
        preparedStatement.close();

        connection.close();
    }
}
