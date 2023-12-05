package hu.nye.wumpus.gameengine;

import java.util.Scanner;

import hu.nye.wumpus.menu.Menu;
import hu.nye.wumpus.model.Board;
import hu.nye.wumpus.model.Hero;

public class Game {
    private Board board;
    private Hero hero;
    private ShootArrow shootArrow;
    private HandleArrowShoot handleArrowShot;
    private int topScore;
    private int arany;

    public Game(Board board, Hero hero) {
        this.board = board;
        this.hero = hero;
        this.handleArrowShot = new HandleArrowShoot(board, hero);
        this.shootArrow = new ShootArrow(board, hero, handleArrowShot);
        this.topScore = 100;
        this.arany = 20;
    }

    public void playGame() {

        Scanner scanner = new Scanner(System.in);

        hero.setNumberOfArrows(heroNumberInitialization(board));

        char action = 'X';

        boolean heroWin = false;

        while (!heroWin && (Character.toUpperCase(action) != 'Q')) {
            Menu.printGamePlayMenu();
            action = scanner.next().charAt(0);

            switch (Character.toUpperCase(action)) {
                case 'L':
                    topScore--;
                    moveHero();
                    printHeroData();
                    printBoard();
                    break;
                case 'B':
                    topScore--;
                    turnHeroLeft();
                    printHeroData();
                    break;
                case 'J':
                    topScore--;
                    turnHeroRight();
                    printHeroData();
                    break;
                case 'S':
                    shootArrow.shootArrow();
                    break;
                case 'Q':
                    System.out.println("Játék vége.");
                    break;
                default:
                    System.out.println("Érvénytelen akció. Kérem, válasszon újra.");
            }
        }
    }

    private int heroNumberInitialization(Board board) {
        int numberOfArrows = 0;
        for (int i = 0; i < board.getSizeOfBoard(); i++) {
            for (int j = 0; j < board.getSizeOfBoard(); j++) {
                if (board.getBoard()[i][j] == 'U') {
                    numberOfArrows++;
                }
            }
        }
        return numberOfArrows;
    }

    private void handleArrowShootWumpus(String message) {
        hero.setNumberOfArrows(hero.getNumberOfArrows() - 1);
        System.out.println(message);
    }

    private void handleArrowShootWall() {
        handleArrowShootWumpus("A falat találtad el, elvesztettél egy nyílat!");
        printHeroData();
        printBoard();
    }

    private void printBoard() {
        int row = hero.getHeroRow() - 1;
        for (int i = 0; i < board.getSizeOfBoard(); i++) {
            for (int j = 0; j < board.getSizeOfBoard(); j++) {
                if (j == hero.getHeroColumn() && i == row) {
                    System.out.print('H');
                } else {
                    System.out.print(board.getBoard()[i][j]);
                }
            }
            System.out.println();
        }
    }

    private void moveHero() {
        int newRow;
        int newColumn;
        if (hero.getHeroDirection() == 'E') {
            if (board.getBoard()[hero.getHeroRow() - 1][hero.getHeroColumn() + 1] == 'W') {
                System.out.println("Fal jön, oda nem léphetsz!");
            } else if (board.getBoard()[hero.getHeroRow() - 1][hero.getHeroColumn() + 1] == 'P') {
                if (hero.getNumberOfArrows() > 0) {
                    hero.setNumberOfArrows(hero.getNumberOfArrows() - 1);
                    hero.setHeroColumn(hero.getHeroColumn() + 1);
                    hero.setHeroRow(hero.getHeroRow());
                    System.out.println("Verembe léptél, elvesztettél egy nyilat!");
                } else {
                    System.out.println("Nincs több nyilad! Vesztettél! Vége a játéknak!");
                }
            } else if (board.getBoard()[hero.getHeroRow() - 1][hero.getHeroColumn() + 1] == 'U') {
                System.out.println("A WUMPUS megölt vége a játéknak!");
            } else if (board.getBoard()[hero.getHeroRow() - 1][hero.getHeroColumn() + 1] == 'G') {
                System.out.println("Felvetted az aranyat! Nyertél!");
                topScore += arany;
                hero.setHasGold(true);
            } else {
                newRow = hero.getHeroRow();
                newColumn = hero.getHeroColumn() + 1;
                if (newRow >= 0 && newRow < board.getSizeOfBoard() && newColumn >= 0 && newColumn < board.getSizeOfBoard()) {
                    hero.setHeroRow(newRow);
                    hero.setHeroColumn(newColumn);
                }
            }
        } else if (hero.getHeroDirection() == 'N') {
            if (board.getBoard()[hero.getHeroRow() - 2][hero.getHeroColumn()] == 'W') {
                System.out.println("Fal jön, oda nem léphetsz!");
            } else if (board.getBoard()[hero.getHeroRow() - 2][hero.getHeroColumn()] == 'P') {
                if (hero.getNumberOfArrows() > 0) {
                    hero.setNumberOfArrows(hero.getNumberOfArrows() - 1);
                    hero.setHeroColumn(hero.getHeroColumn());
                    hero.setHeroRow(hero.getHeroRow() - 1);
                    System.out.println("Verembe léptél, elvesztettél egy nyilat!");
                } else {
                    System.out.println("Nincs több nyilad! Vesztettél! Vége a játéknak!");
                }
            } else if (board.getBoard()[hero.getHeroRow() - 2][hero.getHeroColumn()] == 'U') {
                System.out.println("A WUMPUS megölt vége a játéknak!");
            } else if (board.getBoard()[hero.getHeroRow() - 2][hero.getHeroColumn()] == 'G') {
                System.out.println("Felvetted az aranyat! Nyertél!");
                hero.setHasGold(true);
                topScore += arany;
                System.out.println("TOP SCORE: " + topScore);
            } else {
                newRow = hero.getHeroRow() - 1;
                newColumn = hero.getHeroColumn();
                if (newRow >= 0 && newRow < board.getSizeOfBoard() && newColumn >= 0 && newColumn < board.getSizeOfBoard()) {
                    hero.setHeroRow(newRow);
                    hero.setHeroColumn(newColumn);
                }
            }
        } else if (hero.getHeroDirection() == 'W') {
            if (board.getBoard()[hero.getHeroRow() - 1][hero.getHeroColumn() - 1] == 'W') {
                System.out.println("Fal jön, oda nem léphetsz!");
            } else if (board.getBoard()[hero.getHeroRow() - 1][hero.getHeroColumn() - 1] == 'P') {
                if (hero.getNumberOfArrows() > 0) {
                    hero.setNumberOfArrows(hero.getNumberOfArrows() - 1);
                    hero.setHeroColumn(hero.getHeroColumn() - 1);
                    hero.setHeroRow(hero.getHeroRow());
                    System.out.println("Verembe léptél, elvesztettél egy nyilat!");
                } else {
                    System.out.println("Nincs több nyilad! Vesztettél! Vége a játéknak!");
                }
            } else if (board.getBoard()[hero.getHeroRow() - 1][hero.getHeroColumn() - 1] == 'U') {
                System.out.println("A WUMPUS megölt vége a játéknak!");
            } else if (board.getBoard()[hero.getHeroRow() - 1][hero.getHeroColumn() - 1] == 'G') {
                System.out.println("Felvetted az aranyat! Nyertél!");
                hero.setHasGold(true);
                topScore += arany;
            } else {
                newRow = hero.getHeroRow();
                newColumn = hero.getHeroColumn() - 1;
                if (newRow >= 0 && newRow < board.getSizeOfBoard() && newColumn >= 0 && newColumn < board.getSizeOfBoard()) {
                    hero.setHeroRow(newRow);
                    hero.setHeroColumn(newColumn);
                }
            }
        } else if (hero.getHeroDirection() == 'S') {
            if (board.getBoard()[hero.getHeroRow()][hero.getHeroColumn()] == 'W') {
                System.out.println("Fal jön, oda nem léphetsz!");
            } else if (board.getBoard()[hero.getHeroRow()][hero.getHeroColumn()] == 'P') {
                if (hero.getNumberOfArrows() > 0) {
                    hero.setNumberOfArrows(hero.getNumberOfArrows() - 1);
                    hero.setHeroColumn(hero.getHeroColumn());
                    hero.setHeroRow(hero.getHeroRow() + 1);
                    System.out.println("Verembe léptél, elvesztettél egy nyilat!");
                } else {
                    System.out.println("Nincs több nyilad! Vesztettél! Vége a játéknak!");
                }
            } else if (board.getBoard()[hero.getHeroRow()][hero.getHeroColumn()] == 'U') {
                System.out.println("A WUMPUS megölt vége a játéknak!");
            } else if (board.getBoard()[hero.getHeroRow()][hero.getHeroColumn()] == 'G') {
                System.out.println("Felvetted az aranyat! Nyertél!");
                hero.setHasGold(true);
                topScore += arany;
            } else {
                newRow = hero.getHeroRow() + 1;
                newColumn = hero.getHeroColumn();
                if (newRow >= 0 && newRow < board.getSizeOfBoard() && newColumn >= 0 && newColumn < board.getSizeOfBoard()) {
                    hero.setHeroRow(newRow);
                    hero.setHeroColumn(newColumn);
                }
            }
        }
    }

    private void turnHeroRight() {
        if (hero.getHeroDirection() == 'E') {
            hero.setHeroDirection('S');
        } else if (hero.getHeroDirection() == 'S') {
            hero.setHeroDirection('W');
        } else if (hero.getHeroDirection() == 'W') {
            hero.setHeroDirection('N');
        } else {
            hero.setHeroDirection('E');
        }
    }

    private void printHeroData() {
        System.out.println("Pálya mérete: " + board.getSizeOfBoard());
        System.out.println("Hős pozíciója: " + (char) ('A' + hero.getHeroColumn()) + " " + hero.getHeroRow());
        System.out.println("Hős iránya: " + hero.getHeroDirection());
        System.out.println("Hős nyílainak száma: " + hero.getNumberOfArrows());

    }

    private void turnHeroLeft() {
        if (hero.getHeroDirection() == 'E') {
            hero.setHeroDirection('N');
        } else if (hero.getHeroDirection() == 'N') {
            hero.setHeroDirection('W');
        } else if (hero.getHeroDirection() == 'W') {
            hero.setHeroDirection('S');
        } else {
            hero.setHeroDirection('E');
        }
    }
}
