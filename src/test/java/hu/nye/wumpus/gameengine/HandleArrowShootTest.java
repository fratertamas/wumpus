package hu.nye.wumpus.gameengine;

import hu.nye.wumpus.model.Board;
import hu.nye.wumpus.model.Hero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class HandleArrowShootTest {

    @Test
    public void testHandleArrowShootWallShouldReduceHeroNumberOfArrows() {
        Hero mockHero = mock(Hero.class);
        Board mockBoard = mock(Board.class);
        HandleArrowShoot underTest = new HandleArrowShoot(mockBoard, mockHero);

        mockHero.setNumberOfArrows(1);
        underTest.handleArrowShootWall();

        assertEquals(0, mockHero.getNumberOfArrows());
    }

    @Test
    public void testHandleArrowShootWumpusShouldReduceHeroNumberOfArrows() {
        Hero mockHero = mock(Hero.class);
        Board mockBoard = mock(Board.class);
        HandleArrowShoot underTest = new HandleArrowShoot(mockBoard, mockHero);

        mockHero.setNumberOfArrows(1);
        underTest.handleArrowShootWumpus("Something");

        assertEquals(0, mockHero.getNumberOfArrows());
    }

}
