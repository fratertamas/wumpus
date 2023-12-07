package hu.nye.wumpus.gameengine;

import hu.nye.wumpus.model.Board;
import hu.nye.wumpus.model.Hero;
import hu.nye.wumpus.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HeroMovementHandlerTest {

    private Hero mockHero;
    private Board mockBoard;
    private Player mockPlayer;

    @BeforeEach
    public void setUp() {
        this.mockHero = mock(Hero.class);
        this.mockBoard = mock(Board.class);
        this.mockPlayer = mock(Player.class);
    }

    @Test
    public void testMoveHeroShouldMoveLeftToSpaceCellWhenCalled() throws SQLException {
        char[][] mockBoardArray = {
                {'W', 'W', 'W', 'W', 'W', 'W'},
                {'W', ' ', ' ', ' ', ' ', 'W'},
                {'W', 'U', 'G', 'P', 'W', 'W'},
                {'W', ' ', ' ', ' ', ' ', 'W'},
                {'W', ' ', ' ', 'P', 'W', 'W'},
                {'W', 'W', 'W', 'P', 'W', 'W'},
        };

        when(mockHero.getHeroDirection()).thenReturn('E');
        when(mockHero.getHeroRow()).thenReturn(4);
        when(mockHero.getHeroColumn()).thenReturn(1);
        when(mockBoard.getBoard()).thenReturn(mockBoardArray);

        System.out.println("B : " + mockBoard.getBoard()[4][2]);

        HeroMovementHandler underTest = new HeroMovementHandler(mockPlayer, mockHero, mockBoard);
        underTest.moveHero(10);

        char output = mockBoard.getBoard()[mockHero.getHeroRow()][mockHero.getHeroColumn()];

        assertEquals(' ', output);
    }

    @Test
    public void testMoveHeroShouldMoveLeftToPCellWhenCalled() throws SQLException {
     /*   char[][] mockBoardArray = {
                {'W', 'W', 'W', 'W', 'W', 'W'},
                {'W', ' ', ' ', ' ', ' ', 'W'},
                {'W', 'U', 'G', 'P', 'W', 'W'},
                {'W', ' ', ' ', ' ', ' ', 'W'},
                {'W', ' ', ' ', 'P', 'W', 'W'},
                {'W', 'W', 'W', 'P', 'W', 'W'},
        };

        when(mockHero.getHeroDirection()).thenReturn('E');
        when(mockHero.getHeroRow()).thenReturn(4);
        when(mockHero.getHeroColumn()).thenReturn(2);
        when(mockBoard.getBoard()).thenReturn(mockBoardArray);

        System.out.println("B : " + mockBoard.getBoard()[4][3] + " " +
                "Hero: " + mockHero.getHeroRow() + " " + mockHero.getHeroColumn());
        System.out.println(mockHero.getHeroDirection());

        HeroMovementHandler underTest = new HeroMovementHandler(mockPlayer, mockHero, mockBoard);
        underTest.moveHero(10);

        System.out.println("B2 : " + mockBoard.getBoard()[mockHero.getHeroRow()][mockHero.getHeroColumn()] + " " +
                "Hero2: " + mockHero.getHeroRow() + " " + mockHero.getHeroColumn());

        char output = mockBoard.getBoard()[mockHero.getHeroRow()][mockHero.getHeroColumn()];

        assertEquals('P', output);*/
    }
}
