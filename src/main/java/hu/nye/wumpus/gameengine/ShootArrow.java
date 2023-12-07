package hu.nye.wumpus.gameengine;

import hu.nye.wumpus.model.Arrow;
import hu.nye.wumpus.model.Board;
import hu.nye.wumpus.model.Hero;

/**
 * ShootArrow.
 * Lövés
 */
public class ShootArrow {

    private Arrow arrow;
    private Board board;
    private Hero hero;
    private HandleArrowShoot handleArrowShot;

    public ShootArrow(Board board, Hero hero, Arrow arrow, HandleArrowShoot handleArrowShot) {
        this.board = board;
        this.hero = hero;
        this.arrow =  arrow;
        this.handleArrowShot = handleArrowShot;
    }

    /**
     * shootArrow.
     * lövés
     */
    public void shootArrow() {
        arrow.setArrowDirection(hero.getHeroDirection());
        arrow.setArrowRow(hero.getHeroRow());
        arrow.setArrowColumn(hero.getHeroColumn());
        boolean foundTarget = false;
        if (hero.getNumberOfArrows() > 0) {
            char field;
            if (arrow.getArrowDirection() == 'E') {
                for (int i = arrow.getArrowColumn(); i < board.getSizeOfBoard(); i++) {
                    field = board.getBoard()[arrow.getArrowRow()][i];
                    foundTarget = checkArrowTarget(field, foundTarget);
                }
            } else if (arrow.getArrowDirection() == 'W') {
                for (int i = arrow.getArrowColumn(); i >= 0; i--) {
                    field = board.getBoard()[arrow.getArrowRow()][i];
                    foundTarget =checkArrowTarget(field, foundTarget);
                }
            } else if (arrow.getArrowDirection() == 'N') {
                for (int i = arrow.getArrowRow(); i >= 0; i--) {
                    field = board.getBoard()[i][arrow.getArrowColumn()];
                    foundTarget = checkArrowTarget(field, foundTarget);
                }
            } else if (arrow.getArrowDirection() == 'S') {
                for (int i = arrow.getArrowRow(); i < board.getSizeOfBoard(); i++) {
                    field = board.getBoard()[i][arrow.getArrowColumn()];
                    foundTarget = checkArrowTarget(field, foundTarget);
                }
            }
            if (foundTarget) {
                handleArrowShot.handleArrowShootWumpus("Lelőtted a Wumpust");
            } else {
                handleArrowShot.handleArrowShootWall();
            }
        } else {
            System.out.println("Nincs több nyilad, nem lőhetsz!");
        }
    }

    private boolean checkArrowTarget(char field, boolean foundTarget) {
        if (field == 'U'){
            foundTarget = true;
        }
        return foundTarget;
    }
}
