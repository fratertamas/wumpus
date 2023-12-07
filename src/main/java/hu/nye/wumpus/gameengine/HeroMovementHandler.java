package hu.nye.wumpus.gameengine;

import java.sql.SQLException;

import hu.nye.wumpus.model.Board;
import hu.nye.wumpus.model.Hero;
import hu.nye.wumpus.model.Player;

/**
 * HeroMoveHandler.
 * Hős helyváltoztatásának a kezelése
 */
public class HeroMovementHandler {
    private Board board;
    private Hero hero;
    private Player player;

    public HeroMovementHandler(Player player, Hero hero, Board board) {
        this.board = board;
        this.hero = hero;
        this.player = player;
    }

    /**
     * moveHero.
     * Hős helyváltoztatása
     */
    public void moveHero(int playerScore) throws SQLException {
        handleMove(hero.getHeroDirection());
        int newRow = hero.getHeroRow();
        int newColumn = hero.getHeroColumn();

        if (board.getBoard()[newRow][newColumn] == 'W') {
            System.out.println(handleWall());
        } else if (board.getBoard()[newRow][newColumn] == 'P') {
            System.out.println(handleP());
        } else if (board.getBoard()[newRow][newColumn] == 'U') {
            System.out.println(handleWumpus());
        } else if (board.getBoard()[newRow][newColumn] == 'G') {
            handleGold();
        }
            GameStatusChecker gameStatusChecker = new GameStatusChecker();
            gameStatusChecker.checkGameStatus(player, hero, playerScore);

    }

    private String handleWall() {
        return "Fal jön, oda nem léphetsz!";
    }

    private String handleWumpus() {
        return "A WUMPUS megölt, vége a játéknak!";
    }

    private void handleMove(char direction) {
        int rowChange = 0;
        int columnChange = 0;

        switch (direction) {
            case 'E':
                columnChange = 1;
                break;
            case 'N':
                rowChange = -1;
                break;
            case 'W':
                columnChange = -1;
                break;
            case 'S':
                rowChange = 1;
                break;
            default:
        }

        hero.setHeroRow(hero.getHeroRow() + rowChange);
        hero.setHeroColumn(hero.getHeroColumn() + columnChange);
    }

    private void handleGold() {
        hero.setHasGold(true);
    }

    private String handleP() {
        if (hero.getNumberOfArrows() > 0) {
            hero.setNumberOfArrows(hero.getNumberOfArrows() - 1);
            return "Verembe léptél, elvesztettél egy nyilat!";
        } else {
            return "Nincs több nyilad! Vesztettél! Vége a játéknak!";
        }
    }
}
