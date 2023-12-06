package hu.nye.wumpus.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
