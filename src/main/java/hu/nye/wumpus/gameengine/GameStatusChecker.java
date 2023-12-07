package hu.nye.wumpus.gameengine;

import java.sql.SQLException;

import hu.nye.wumpus.database.TopScoreQuery;
import hu.nye.wumpus.model.Hero;
import hu.nye.wumpus.model.Player;

/**
 * Gamastatuschecker.
 * Game állapot ellenúrzás
 */
public class GameStatusChecker {

    /**
     * checlGameStatus.
     * Aramy vozsgűéat
     */
    public void checkGameStatus(Player player, Hero hero, int playerScore) throws SQLException {
        if (hero.getHasGold()) {
            handleVictory(player, playerScore);
        }
    }

    private void handleVictory(Player player, int playerScore) throws SQLException {
        System.out.println("Felvetted az aranyat! Nyertél!");
        playerScore += 10;
        System.out.println("TOP SCORE: " + playerScore);
        TopScoreQuery topScoreQuery = new TopScoreQuery();
        topScoreQuery.saveTopScore(player, playerScore);
    }
}
