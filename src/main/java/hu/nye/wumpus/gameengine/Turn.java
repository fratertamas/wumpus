package hu.nye.wumpus.gameengine;

import hu.nye.wumpus.model.Hero;

public class Turn {
    private Hero hero;

    public Turn(Hero hero) {
        this.hero = hero;
    }

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
