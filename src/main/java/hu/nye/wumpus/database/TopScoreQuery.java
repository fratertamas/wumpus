package hu.nye.wumpus.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TopScoreQuery {

    public void getTopScore() throws SQLException {
        // Get a connection to the database
        Connection connection = DatabaseConnection.getConnection();

        // Execute a query
        String query = "SELECT player, score FROM topscore";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        /// Execute the prepared statement
        ResultSet resultSet = preparedStatement.executeQuery();

        // Iterate over the results
        while (resultSet.next()) {
            String player = resultSet.getString("player");
            int score = resultSet.getInt("score");

            // Print the results
            System.out.println("Player: " + player + ", Score: " + score);
        }

        // Close the prepared statement
        resultSet.close();
        preparedStatement.close();

        // Close the connection
        connection.close();
    }
}
