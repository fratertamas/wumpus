package hu.nye.wumpus.utils;


import hu.nye.wumpus.model.Board;
import hu.nye.wumpus.model.Hero;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DisplayUtilsTest {

    @Test
    public void testPrintHeroDataShouldPrintHeroDataWhenCalled() {
        Hero mockHero = mock(Hero.class);
        Board mockBoard = mock(Board.class);

        when(mockBoard.getSizeOfBoard()).thenReturn(6);
        when(mockHero.getHeroColumn()).thenReturn(1);
        when(mockHero.getHeroRow()).thenReturn(5);
        when(mockHero.getHeroDirection()).thenReturn('E');

        DisplayUtils displayUtils = new DisplayUtils(mockBoard, mockHero);
        displayUtils.printHeroData();

        verify(mockBoard, times(1)).getSizeOfBoard();
        verify(mockHero, times(1)).getHeroColumn();
        verify(mockHero, times(1)).getHeroRow();
        verify(mockHero, times(1)).getHeroDirection();

        assertEquals(6, mockBoard.getSizeOfBoard());
        assertEquals(1, mockHero.getHeroColumn());
        assertEquals(5, mockHero.getHeroRow());
        assertEquals('E', mockHero.getHeroDirection());
    }

    @Test
    public void testPrintBoardShouldPrintBoardCalledWhenCalled() throws Exception {
        // Arrange
        Board mockBoard = mock(Board.class);
        Hero mockHero = mock(Hero.class);

        when(mockBoard.getSizeOfBoard()).thenReturn(6);
        when(mockHero.getHeroColumn()).thenReturn(1);
        when(mockHero.getHeroRow()).thenReturn(5);
        when(mockHero.getHeroDirection()).thenReturn('E');
        when(mockBoard.getBoard()).thenReturn( new char[][]{
                {'W', 'W', 'W', 'W', 'W', 'W'},
                {'W', ' ', ' ', ' ', ' ', 'W'},
                {'W', 'U', 'G', 'P', 'W', 'W'},
                {'W', ' ', ' ', ' ', ' ', 'W'},
                {'W', 'H', ' ', 'P', 'W', 'W'},
                {'W', 'W', 'W', 'W', 'W', 'W'},
        });
        DisplayUtils displayUtils = new DisplayUtils(mockBoard, mockHero);
        displayUtils.printBoard();

        Mockito.verify(mockBoard, Mockito.times(35)).getBoard();
    }
}