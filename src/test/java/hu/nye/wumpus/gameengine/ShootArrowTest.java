package hu.nye.wumpus.gameengine;

import hu.nye.wumpus.model.Arrow;
import hu.nye.wumpus.model.Board;
import hu.nye.wumpus.model.Hero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ShootArrowTest {
    @Test
    public void testShootArrowShouldHandleArrowShootWallWhenDirectionE() {
        Hero mockHero = mock(Hero.class);
        Board mockBoard = mock(Board.class);
        Arrow mockArrow = mock(Arrow.class);
        HandleArrowShoot mockHandleArrowShoot = mock(HandleArrowShoot.class);


        when(mockHero.getNumberOfArrows()).thenReturn(1);
        when(mockArrow.getArrowRow()).thenReturn(4);
        when(mockArrow.getArrowColumn()).thenReturn(2);
        when(mockArrow.getArrowDirection()).thenReturn('E');
        when(mockBoard.getSizeOfBoard()).thenReturn(6);

        char[][] mockBoardArray = {
                        {'W', 'W', 'W', 'W', 'W', 'W'},
                        {'W', ' ', ' ', ' ', ' ', 'W'},
                        {'W', 'U', 'G', 'P', 'W', 'W'},
                        {'W', ' ', ' ', ' ', ' ', 'W'},
                        {'W', ' ', ' ', 'P', 'W', 'W'},
                        {'W', 'W', 'W', 'P', 'W', 'W'},
        };
        when(mockBoard.getBoard()).thenReturn(mockBoardArray);
        ShootArrow underTest = new ShootArrow(mockBoard, mockHero, mockHandleArrowShoot);
        underTest.shootArrow();

        verify(mockHero, times(1)).getNumberOfArrows();

        //verify(mockArrow).getArrowDirection();
    }
}
