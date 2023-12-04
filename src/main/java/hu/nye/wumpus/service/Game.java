package hu.nye.wumpus.service;

import hu.nye.wumpus.data.FileLoader;
import hu.nye.wumpus.model.Board;
import hu.nye.wumpus.menu.Menu;

import java.util.Scanner;
    public class Game {
    private static Board board;

    public static void playGame() {
        FileLoader fileLoader = new FileLoader();
        board = fileLoader.loadFromFile("wumpuszinput.txt");

        Scanner scanner;
        scanner = new Scanner(System.in);

        char action;

        boolean heroWin = false;
        do {
            Menu.printGamePlayMenu();
            action = scanner.next().charAt(0);

            switch (action) {
                case 'L':
                    //move(heroDirection, heroColumn, heroRow, field);
                    break;
                case 'B':
                    //turnLeft();
                    break;
                case 'J':
                    //turnRight(heroDirection);
                    break;
                case 'S':
                    //shoot();
                    break;
                case 'Q':
                    System.out.println("Játék vége.");
                    //System.exit(0);
                    break;
                case 'q':
                    System.out.println("Játék vége.");
                    //System.exit(0);
                    break;
                default:
                    System.out.println("Érvénytelen akció. Kérem, válasszon újra.");

            }
        }while (!heroWin && (action != 'Q' && action != 'q'));

    }

    private static void turnLeft(){

    }
}

