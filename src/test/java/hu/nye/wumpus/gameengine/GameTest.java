package hu.nye.wumpus.gameengine;

import hu.nye.wumpus.database.GameQuery;
import hu.nye.wumpus.io.saver.impl.JsonGameSaver;
import hu.nye.wumpus.io.saver.impl.XmlGameSaver;
import hu.nye.wumpus.model.Board;
import hu.nye.wumpus.model.Hero;
import hu.nye.wumpus.model.Player;
import org.junit.jupiter.api.Test;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class GameTest {

    @Test
    public void createSaveStringShouldCreateCorrectStringForBoard() {
        // Arrange
        Board mockBoard = mock(Board.class);
        Hero mockHero = mock(Hero.class);
        Player mockPlayer = mock(Player.class);

        char[][] mockBoardArray = {
                {'W', 'W', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', '_', '_', 'W'},
                {'W', 'U', 'G', 'P', '_', 'W'},
                {'W', '_', '_', '_', '_','W'},
                {'W', '_', '_', 'P', '_', 'W'},
                {'W', 'W', 'W', 'W', 'W', 'W'},
        };

        when(mockHero.getHeroDirection()).thenReturn('N');
        when(mockHero.getHeroRow()).thenReturn(2);
        when(mockHero.getHeroColumn()).thenReturn(1);
        when(mockBoard.getBoard()).thenReturn(mockBoardArray);
        when(mockHero.getNumberOfArrows()).thenReturn(2);
        when(mockBoard.getSizeOfBoard()).thenReturn(6);

        String expectedSaveString = "6 B 2 N WWWWWW W____W WUGP_W W____W W__P_W WWWWWW";
        Game underTest = new Game(mockBoard, mockHero, mockPlayer);
        String actualSaveString = underTest.createSaveString();

        assertEquals(expectedSaveString, actualSaveString);

    }

    @Test
    public void testHeroNumberOfArrowInitialization() {
        Board mockBoard = mock(Board.class);
        Hero mockHero = mock(Hero.class);
        Player mockPlayer = mock(Player.class);

        char[][] mockBoardArray = {
                {'W', 'W', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', '_', '_', 'W'},
                {'W', 'U', 'G', 'P', '_', 'W'},
                {'W', '_', '_', '_', '_','W'},
                {'W', '_', '_', 'P', '_', 'W'},
                {'W', 'W', 'W', 'W', 'W', 'W'},
        };
        when(mockHero.getHeroDirection()).thenReturn('N');
        when(mockHero.getHeroRow()).thenReturn(2);
        when(mockHero.getHeroColumn()).thenReturn(1);
        when(mockBoard.getBoard()).thenReturn(mockBoardArray);
        when(mockHero.getNumberOfArrows()).thenReturn(2);
        when(mockBoard.getSizeOfBoard()).thenReturn(6);

        int expected = 1;
        Game underTest = new Game(mockBoard, mockHero, mockPlayer, 42);
        int actual = underTest.heroNumberOfArrowsInitialization(mockBoard);

        assertEquals(expected, actual);
    }
}
