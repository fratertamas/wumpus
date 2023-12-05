package hu.nye.wumpus.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TopScore {

    public void topScore() throws SQLException {
        // Get a connection to the database
        Connection connection = DatabaseConnection.getConnection();

        // Execute a query
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT player, score FROM topscore");

        // Iterate over the results
        while (resultSet.next()) {
            String player = resultSet.getString("player");
            int score = resultSet.getInt("score");

            // Print the results
            System.out.println("Player: " + player + ", Score: " + score);
        }

        // Close the connection
        connection.close();
    }
}
