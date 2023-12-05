package hu.nye.wumpus.service;

import hu.nye.wumpus.model.Arrow;
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
                    move();
                    printHeroData();
                    printBoard();
                    
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
                    shoot();
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

    private static void shoot() {
        if (hero.getNumberOfArrows() > 0){
            Arrow arrow = new Arrow(hero.getHeroRow(), hero.getHeroColumn(), hero.getHeroDirection());
            char field;
            if (arrow.getArrowDirection() == 'E') {
                for (int i = arrow.getArrowColumn(); i < board.getSizeOfBoard(); i++) {
                    field = board.getBoard()[arrow.getArrowRow()-1][i];
                    if (field == 'W') {
                        hero.setNumberOfArrows(hero.getNumberOfArrows()-1);
                        System.out.println("A falat találtad el, elvesztettél egy nyílat!");
                        printHeroData();
                        printBoard();
                    }else if(field == 'U') {
                        hero.setNumberOfArrows(hero.getNumberOfArrows()-1);
                        System.out.println("Lelőtted a Wumpust, nyertél!");
                    }
                }
            }else if (arrow.getArrowDirection() == 'W') {
                for (int i = arrow.getArrowColumn(); i >= 0; i--) {
                    field = board.getBoard()[arrow.getArrowRow()-1][i];
                    if (field == 'W') {
                        hero.setNumberOfArrows(hero.getNumberOfArrows()-1);
                        System.out.println("A falat találtad el, elvesztettél egy nyílat!");
                        printHeroData();
                        printBoard();
                    }else if(field == 'U') {
                        hero.setNumberOfArrows(hero.getNumberOfArrows()-1);
                        System.out.println("Lelőtted a Wumpust, nyertél!");
                    }
                }
            } else if (arrow.getArrowDirection() == 'N') {
                for (int i = arrow.getArrowRow()-1; i >= 0; i--){
                    field = board.getBoard()[i][arrow.getArrowColumn()];
                    System.out.println(field);
                    if (field == 'W') {
                        hero.setNumberOfArrows(hero.getNumberOfArrows()-1);
                        System.out.println("A falat találtad el, elvesztettél egy nyílat!");
                        printHeroData();
                        printBoard();
                    }else if(field == 'U') {
                        hero.setNumberOfArrows(hero.getNumberOfArrows()-1);
                        System.out.println("Lelőtted a Wumpust, nyertél!");
                    }
                }
            } else {
                for (int i = arrow.getArrowRow()-1; i < board.getSizeOfBoard(); i++){
                    field = board.getBoard()[i][arrow.getArrowColumn()];
                    if (field == 'W') {
                        hero.setNumberOfArrows(hero.getNumberOfArrows()-1);
                        System.out.println("A falat találtad el, elvesztettél egy nyílat!");
                        printHeroData();
                        printBoard();
                    }else if(field == 'U') {
                        hero.setNumberOfArrows(hero.getNumberOfArrows()-1);
                        System.out.println("Lelőtted a Wumpust, nyertél!");
                    }
                }
            }
        }else{
            System.out.println("Nincs több nyilad, nem lőhetsz!");
        }
    }

    private static void printBoard() {
        int row = hero.getHeroRow()-1;
        for (int i = 0; i < board.getSizeOfBoard(); i++) {
            for (int j = 0; j < board.getSizeOfBoard(); j++) {
                if (j == hero.getHeroColumn() && i == row){
                    System.out.print('H');
                }else{
                    System.out.print(board.getBoard()[i][j]);
                }
            }
            System.out.println();
        }
    }

    private static void move() {
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
                hero.setHasGold(true);
                //TOPSCORE
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
                //TOPSCORE
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
                //TOPSCORE
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
                //TOPSCORE
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

    private static void turnRight () {
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

    private static void printHeroData () {
        System.out.println("Pálya mérete: " + board.getSizeOfBoard());
        System.out.println("Hős pozíciója: " + (char) ('A' + hero.getHeroColumn()) + " " + hero.getHeroRow());
        System.out.println("Hős iránya: " + hero.getHeroDirection());
        System.out.println("Hős nyílainak száma: " + hero.getNumberOfArrows());

    }

    private static void turnLeft () {
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

