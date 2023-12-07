package hu.nye.wumpus.gameengine;

import hu.nye.wumpus.model.Hero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TurnTest {

    private Hero mockHero;

    @BeforeEach
    public void setUp(){
        this.mockHero = mock(Hero.class);
    }

    @Test
    public void testTurnHeroRightSouldTurnRightESWhenCalled(){
        when(mockHero.getHeroDirection()).thenReturn('E');

        Turn underTest = new Turn(mockHero);
        char newDirection = underTest.turnHeroRight();

        assertEquals('S', newDirection);
    }

    @Test
    public void testTurnHeroRightSouldTurnRightSWWhenCalled(){
        when(mockHero.getHeroDirection()).thenReturn('S');

        Turn underTest = new Turn(mockHero);
        char newDirection = underTest.turnHeroRight();

        assertEquals('W', newDirection);
    }

    @Test
    public void testTurnHeroRightSouldTurnRightWNWhenCalled(){
        when(mockHero.getHeroDirection()).thenReturn('W');

        Turn underTest = new Turn(mockHero);
        char newDirection = underTest.turnHeroRight();

        assertEquals('N', newDirection);
    }

    @Test
    public void testTurnHeroRightSouldTurnRightNEWhenCalled(){
        when(mockHero.getHeroDirection()).thenReturn('N');

        Turn underTest = new Turn(mockHero);
        char newDirection = underTest.turnHeroRight();

        assertEquals('E', newDirection);
    }

    @Test
    public void testTurnHeroLeftSouldTurnRightENWhenCalled(){
        when(mockHero.getHeroDirection()).thenReturn('E');

        Turn underTest = new Turn(mockHero);
        char newDirection = underTest.turnHeroLeft();

        assertEquals('N', newDirection);
    }
    @Test
    public void testTurnHeroLeftSouldTurnRightNWWhenCalled(){
        when(mockHero.getHeroDirection()).thenReturn('N');

        Turn underTest = new Turn(mockHero);
        char newDirection = underTest.turnHeroLeft();

        assertEquals('W', newDirection);
    }

    @Test
    public void testTurnHeroLeftSouldTurnRightWSWhenCalled(){
        when(mockHero.getHeroDirection()).thenReturn('W');

        Turn underTest = new Turn(mockHero);
        char newDirection = underTest.turnHeroLeft();

        assertEquals('S', newDirection);
    }
    @Test
    public void testTurnHeroLeftSouldTurnRightSEWhenCalled(){
        when(mockHero.getHeroDirection()).thenReturn('S');

        Turn underTest = new Turn(mockHero);
        char newDirection = underTest.turnHeroLeft();

        assertEquals('E', newDirection);
    }
}
