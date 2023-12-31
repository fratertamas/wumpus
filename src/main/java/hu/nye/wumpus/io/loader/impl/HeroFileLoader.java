package hu.nye.wumpus.io.loader.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import hu.nye.wumpus.io.loader.FileLoader;
import hu.nye.wumpus.model.Hero;

/**
 * HeroFileLoader.
 * Hős betöltése fájlból
 */
public class HeroFileLoader implements FileLoader {
    @Override
    public Hero load(String fileName) throws IOException {
        File file = new File("src/main/resources/" + fileName);
        FileInputStream inputStream = new FileInputStream(file);
        Scanner scanner = new Scanner(inputStream);

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
