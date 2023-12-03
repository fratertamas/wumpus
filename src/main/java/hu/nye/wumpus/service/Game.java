package hu.nye.wumpus.service;

import hu.nye.wumpus.data.FileLoader;
import hu.nye.wumpus.model.Board;
import hu.nye.wumpus.menu.Menu;

public class Game {
    private static Board board;
    public static void playGame() {
        FileLoader fileLoader = new FileLoader();
        board = fileLoader.loadFromFile("wumpuszinput.txt");

        Menu.printGamePlayMenu();

    }
}
