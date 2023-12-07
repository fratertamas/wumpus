package hu.nye.wumpus.gameengine;

import hu.nye.wumpus.model.Board;
import hu.nye.wumpus.model.Hero;
import hu.nye.wumpus.model.Player;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class GameTest {
/*
    @Test
    public void testDbGameSave() throws SQLException {
        // Arrange
        Board mockBoard = mock(Board.class);
        Hero mockHero = mock(Hero.class);
        Player mockPlayer = mock(Player.class);
        GameQuery mockGameQuery = mock(GameQuery.class);

        Game game = new Game(mockBoard, mockHero, mockPlayer);
        game.setGameQuery(mockGameQuery); // A setterrel injektáljuk a mock GameQuery-t

        // Act
        game.dbGameSave();

        // Assert
        verify(mockGameQuery, times(1)).saveGame(anyString(), anyString(), anyInt());
        // Itt ellenőrizzük, hogy a saveGame metódus pontosan egyszer hízva lett a megfelelő paraméterekkel
    }
    /*@Test
    public void testCreateSaveStringSouldCreateStringWhenCalled() {
        Hero mockHero = mock(Hero.class);
        Board mockBoard = mock(Board.class);
        Player mockPlayer = mock(Player.class);

        Game underTest = new Game(mockBoard, mockHero, mockPlayer);

        String result = underTest

        // Tesztelt metódus hívása
        YourClass yourObject = new YourClass(board, hero);
        String result = yourObject.createSaveString();

        // Elvárt eredmény
        String expected = "6 C 4 N WWWWWW W___PW WUGP_W W____W W__P_W WWWWWW";

        // Ellenőrzés
        assertEquals(expected, result);
    }*/
}
