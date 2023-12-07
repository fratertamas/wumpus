package hu.nye.wumpus.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import hu.nye.wumpus.model.Player;

/**
 * TopScareQuery.
 * Toplista adatbázisból
 */
public class TopScoreQuery {

    /**
     * getTopScore.
     * Toplista lekérdezése
     */
    public void getTopScore() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();

        String query = "SELECT player, score FROM topscore ORDER BY score DESC";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String player = resultSet.getString("player");
            int score = resultSet.getInt("score");

            System.out.println("Player: " + player + ", Score: " + score);
        }

        resultSet.close();
        preparedStatement.close();

        connection.close();
    }

    /**
     * saveTopScore.
     * Gyúzelem esetén új győztes adatainak felvitele
     */
    public void saveTopScore(Player player, int score) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();

        String query = "INSERT INTO topscore (player, score) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, player.getPlayerName());
        preparedStatement.setInt(2, score);

        preparedStatement.executeUpdate();

        connection.close();
    }
}
