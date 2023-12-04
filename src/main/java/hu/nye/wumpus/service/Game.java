package hu.nye.wumpus.service;

import hu.nye.wumpus.data.FileLoader;
import hu.nye.wumpus.model.Board;
import hu.nye.wumpus.menu.Menu;
import hu.nye.wumpus.model.Hero;

import java.util.Scanner;
public class Game {
    private static Board board;
    private static Hero hero;

    public Game(Board board, Hero hero) {
        this.board = board;
        this.hero = hero;
    }

    public static void playGame() {
        Scanner scanner = new Scanner(System.in);

        char action;

        boolean heroWin = false;
        do {
            Menu.printGamePlayMenu();
            action = scanner.next().charAt(0);

            switch (Character.toUpperCase(action)) {
                case 'L':
                    //move(heroDirection, heroColumn, heroRow, field);
                    break;
                case 'B':
                    turnLeft();
                    printHeroData();
                    break;
                case 'J':
                    turnRight();
                    printHeroData();
                    break;
                case 'S':
                    //shoot();
                    break;
                case 'Q':
                    System.out.println("Játék vége.");
                    //System.exit(0);
                    break;
                default:
                    System.out.println("Érvénytelen akció. Kérem, válasszon újra.");

            }
        } while (!heroWin && (Character.toUpperCase(action) != 'Q'));
}

    private static void turnRight() {
        if (hero.getHeroDirection() == 'E'){
            hero.setHeroDirection('S');
        }else if (hero.getHeroDirection() == 'S'){
            hero.setHeroDirection('W');
        }else if (hero.getHeroDirection() == 'W'){
            hero.setHeroDirection('N');
        }else{
            hero.setHeroDirection('E');
        }
    }

    private static void printHeroData() {
        System.out.println("Pálya mérete: " + board.getSizeOfBoard());
        System.out.println("Hős pozíciója: " + (char)('A' + hero.getHeroColumn())  + " " + hero.getHeroRow());
        System.out.println("Hős iránya: " + hero.getHeroDirection());
        System.out.println("Hős nyílainak száma: " + hero.getNumberOfArrows());

    }

    private static void turnLeft(){
        if (hero.getHeroDirection() == 'E'){
            hero.setHeroDirection('N');
        }else if (hero.getHeroDirection() == 'N'){
            hero.setHeroDirection('W');
        }else if (hero.getHeroDirection() == 'W'){
            hero.setHeroDirection('S');
        }else{
            hero.setHeroDirection('E');
        }
    }
}

