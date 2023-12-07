package hu.nye.wumpus.io.loader.impl;

import java.util.Scanner;

import hu.nye.wumpus.io.loader.FileLoader;
import hu.nye.wumpus.model.Hero;

/**
 * HeroDBLoader.
 * Hős betöltése DB-ből
 */
public class HeroDBLoader implements FileLoader {
    @Override
    public Hero load(String gameState) {
        Scanner scanner = new Scanner(gameState);

        int size = scanner.nextInt();

        //A hős poziciója és iránya
        int heroColumn = scanner.next().charAt(0) - 'A';
        int heroRow = scanner.nextInt() - 1;
        char heroDirection = scanner.next().charAt(0);

        scanner.close();
        Hero hero = new Hero(heroColumn, heroRow, heroDirection, false);

        return hero;
    }
}
