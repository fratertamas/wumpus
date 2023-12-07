package hu.nye.wumpus.gameengine;

import hu.nye.wumpus.model.Board;
import hu.nye.wumpus.model.Hero;
import hu.nye.wumpus.utils.DisplayUtils;

import java.util.logging.Logger;

public class HandleArrowShoot {

    private Hero hero;
    private Board board;
    private DisplayUtils displayUtils;

    //private static final Logger LOGGER = Logger.getLogger(HandleArrowShoot.class.getName());

    public HandleArrowShoot(Board board, Hero hero) {
        this.board = board;
        this.hero = hero;
        this.displayUtils = new DisplayUtils(board, hero);
    }

    public void handleArrowShootWumpus(String message) {
        hero.setNumberOfArrows(hero.getNumberOfArrows() - 1);
        System.out.println(message);
    }

    public void handleArrowShootWall() {
        hero.setNumberOfArrows(hero.getNumberOfArrows() - 1);
        System.out.println("A falat találtad el, elvesztettél egy nyílat!");
        displayUtils.printHeroData();
        displayUtils.printBoard();
    }
}
