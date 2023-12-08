package hu.nye.wumpus.gameengine;

import hu.nye.wumpus.database.TopScoreQuery;
import hu.nye.wumpus.model.Hero;
import hu.nye.wumpus.model.Player;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GameStatusCheckerTest {

    @Test
    public void testCheckGameStatusShouldHeroHasGoldWhenCalled() throws SQLException {
    /*    Player mockPlayer = mock(Player.class);
        Hero mockHero = mock(Hero.class);
        TopScoreQuery mockTopScoreQuery = mock(TopScoreQuery.class);

        when(mockHero.getHasGold()).thenReturn(true);
        when(mockPlayer.getPlayerName()).thenReturn("Teszt Elek");
        int playerScore = 42;

        GameStatusChecker underTest = new GameStatusChecker();
        underTest.checkGameStatus(mockPlayer, mockHero, playerScore);

        assertEquals(52, playerScore);

*/
    }
}
