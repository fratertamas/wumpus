package hu.nye.wumpus.utils;

import hu.nye.wumpus.model.Board;
import hu.nye.wumpus.model.Hero;

/**
 * DisplayUtils.
 * Játék megjelenítés
 */
public class DisplayUtils {
    private final Board board;
    private final Hero hero;

    public DisplayUtils(Board board, Hero hero) {
        this.board = board;
        this.hero = hero;
    }

    /**
     * printBoard.
     * Játéktér kiírása
     */
    public void printBoard() {
        int row = hero.getHeroRow();
        for (int i = 0; i < board.getSizeOfBoard(); i++) {
            for (int j = 0; j < board.getSizeOfBoard(); j++) {
                if (j == hero.getHeroColumn() && i == row) {
                    System.out.print('H');
                } else {
                    System.out.print(board.getBoard()[i][j]);
                }
            }
            System.out.println();
        }
    }

    /**
     * printHeroData.
     * Hős adatainak kiírása
     */
    public void printHeroData() {
        System.out.println("Pálya mérete: " + board.getSizeOfBoard());
        System.out.println("Hős pozíciója: " + (char) ('A' + hero.getHeroColumn()) + " " + (hero.getHeroRow() + 1));
        System.out.println("Hős iránya: " + hero.getHeroDirection());
        System.out.println("Hős nyílainak száma: " + hero.getNumberOfArrows());
    }

}
