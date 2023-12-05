package hu.nye.wumpus.gameengine;

import hu.nye.wumpus.model.Board;
import hu.nye.wumpus.model.Hero;

public class HandleArrowShoot {

    private Hero hero;
    private Board board;

    public HandleArrowShoot(Board board, Hero hero) {
        this.board = board;
        this.hero = hero;
    }

    public void handleArrowShootWumpus(String message) {
        hero.setNumberOfArrows(hero.getNumberOfArrows() - 1);
        System.out.println(message);
    }

    public void handleArrowShootWall() {
        System.out.println("A falat találtad el, elvesztettél egy nyílat!");
        printHeroData();
        printBoard();
    }

    private void printBoard() {
        int row = hero.getHeroRow() - 1;
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

    private void printHeroData() {
        System.out.println("Pálya mérete: " + board.getSizeOfBoard());
        System.out.println("Hős pozíciója: " + (char) ('A' + hero.getHeroColumn()) + " " + hero.getHeroRow());
        System.out.println("Hős iránya: " + hero.getHeroDirection());
        System.out.println("Hős nyílainak száma: " + hero.getNumberOfArrows());
    }
}
