package hu.nye.wumpus.gameengine;

import hu.nye.wumpus.model.Arrow;
import hu.nye.wumpus.model.Board;
import hu.nye.wumpus.model.Hero;

public class ShootArrow {

    private Board board;
    private Hero hero;
    private HandleArrowShoot handleArrowShot;

    public ShootArrow(Board board, Hero hero, HandleArrowShoot handleArrowShot) {
        this.board = board;
        this.hero = hero;
        this.handleArrowShot = handleArrowShot;
    }

    public void shootArrow() {
        if (hero.getNumberOfArrows() > 0) {
            Arrow arrow = new Arrow(hero.getHeroRow(), hero.getHeroColumn(), hero.getHeroDirection());
            char field;
            if (arrow.getArrowDirection() == 'E') {
                for (int i = arrow.getArrowColumn(); i < board.getSizeOfBoard(); i++) {
                    field = board.getBoard()[arrow.getArrowRow()-1][i];
                    if (field == 'W') {
                        handleArrowShot.handleArrowShootWall();
                    }else if(field == 'U') {
                        handleArrowShot.handleArrowShootWumpus("Lelőtted a Wumpust, nyertél!");
                    }
                }
            }else if (arrow.getArrowDirection() == 'W') {
                for (int i = arrow.getArrowColumn(); i >= 0; i--) {
                    field = board.getBoard()[arrow.getArrowRow()-1][i];
                    if (field == 'W') {
                        handleArrowShot.handleArrowShootWall();
                    }else if(field == 'U') {
                        handleArrowShot.handleArrowShootWumpus("Lelőtted a Wumpust, nyertél!");
                    }
                }
            } else if (arrow.getArrowDirection() == 'N') {
                for (int i = arrow.getArrowRow()-1; i >= 0; i--){
                    field = board.getBoard()[i][arrow.getArrowColumn()];
                    if (field == 'W') {
                        handleArrowShot.handleArrowShootWall();
                    }else if(field == 'U') {
                        handleArrowShot.handleArrowShootWumpus("Lelőtted a Wumpust, nyertél!");
                    }
                }
            } else if (arrow.getArrowDirection() == 'S') {
                for (int i = arrow.getArrowRow()-1; i < board.getSizeOfBoard(); i++){
                    field = board.getBoard()[i][arrow.getArrowColumn()];
                    if (field == 'W') {
                        handleArrowShot.handleArrowShootWall();
                    }else if(field == 'U') {
                        handleArrowShot.handleArrowShootWumpus("Lelőtted a Wumpust, nyertél!");
                    }
                }
            }
        }else{
            System.out.println("Nincs több nyilad, nem lőhetsz!");
        }
    }
}
