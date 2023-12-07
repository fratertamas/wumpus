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



public class GameQuery {
    public void saveGame(Player player, String save, int playerScore) throws SQLException {
        System.out.println("Player: " + player.getPlayerName() + "\n" +
                save);
        // Get a connection to the database
        Connection connection = DatabaseConnection.getConnection();

        // Prepare the statement
        String query = "INSERT INTO games (player, game, score) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        // Set the parameters
        preparedStatement.setString(1, player.getPlayerName());
        preparedStatement.setString(2, save);
        preparedStatement.setInt(3, playerScore);

        // Execute the statement
        preparedStatement.executeUpdate();

        // Close the connection
        connection.close();
    }

    public void listSavedGames() throws SQLException {
        // Get a connection to the database
        Connection connection = DatabaseConnection.getConnection();

        // Execute a query
        String query = "SELECT * FROM games";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        /// Execute the prepared statement
        ResultSet resultSet = preparedStatement.executeQuery();
        // Iterate over the results
        while (resultSet.next()) {
            int gameId = resultSet.getInt("gameid");
            String player = resultSet.getString("player");
            String board = resultSet.getString("game");
            int score = resultSet.getInt("score");

            // Print the results
            System.out.println("GameID: " + gameId + "Player: " + player + ", Score: " + score);
        }

        // Close the prepared statement
        resultSet.close();
        preparedStatement.close();

        // Close the connection
        connection.close();
    }

    public void loadGame(String gameId) throws SQLException, XMLStreamException, IOException {
        // Get a connection to the database
        Connection connection = DatabaseConnection.getConnection();

        // Execute a query
        String query = "SELECT player, game, score FROM games WHERE gameid = " + gameId + ";";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        /// Execute the prepared statement
        ResultSet resultSet = preparedStatement.executeQuery();
        // Iterate over the results
        while (resultSet.next()) {
            String player = resultSet.getString("player");
            String board = resultSet.getString("game");
            int score = resultSet.getInt("score");

            // Print the results
            System.out.println("Player: " + player + ", Board: " + board + ", Score: " + score);

            BoardDBLoader boardDBLoader = new BoardDBLoader();
            HeroDBLoader heroDBLoader = new HeroDBLoader();

            System.out.println(boardDBLoader.load(board));

            Game game = new Game(boardDBLoader.load(board), heroDBLoader.load(board), new Player(player), score);
            game.playGame();
        }

        // Close the prepared statement
        resultSet.close();
        preparedStatement.close();

        // Close the connection
        connection.close();
    }
}
