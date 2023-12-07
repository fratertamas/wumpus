package hu.nye.wumpus.gameengine;

import hu.nye.wumpus.model.Hero;

/**
 * Turn.
 * Fodulási események
 */
public class Turn {
    private Hero hero;

    public Turn(Hero hero) {
        this.hero = hero;
    }

    /**
     * turmHerpRight.
     * A hős jobbra fordulása
     */
    public char turnHeroRight() {
        if (hero.getHeroDirection() == 'E') {
            return 'S';
        } else if (hero.getHeroDirection() == 'S') {
            return 'W';
        } else if (hero.getHeroDirection() == 'W') {
            return 'N';
        } else {
            return 'E';
        }
    }

    /**
     * turnHeroLeft.
     * A hős balra fordulása
     */
    public char turnHeroLeft() {
        if (hero.getHeroDirection() == 'E') {
            return 'N';
        } else if (hero.getHeroDirection() == 'N') {
            return 'W';
        } else if (hero.getHeroDirection() == 'W') {
            return 'S';
        } else {
            return 'E';
        }
    }
}
