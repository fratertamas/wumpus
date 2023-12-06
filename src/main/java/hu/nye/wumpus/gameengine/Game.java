package hu.nye.wumpus.gameengine;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import hu.nye.wumpus.database.GameQuery;
import hu.nye.wumpus.menu.Menu;
import hu.nye.wumpus.model.Board;
import hu.nye.wumpus.model.Hero;
import hu.nye.wumpus.model.Player;
import hu.nye.wumpus.serialization.impl.JsonGameSaver;
import hu.nye.wumpus.serialization.impl.XmlGameSaver;

import javax.xml.stream.XMLStreamException;

public class Game {
    private Board board;
    private Hero hero;
    private ShootArrow shootArrow;
    private HandleArrowShoot handleArrowShot;
    private int playerScore;
    private static final int VALUE_OF_GOLD = 20;
    private Player player;

    public Game(Board board, Hero hero, Player player) {
        this.board = board;
        this.hero = hero;
        this.handleArrowShot = new HandleArrowShoot(board, hero);
        this.shootArrow = new ShootArrow(board, hero, handleArrowShot);
        this.playerScore = 100;
        this.player = player;
    }

    public void playGame() throws SQLException, IOException, XMLStreamException {

        Scanner scanner = new Scanner(System.in);

        hero.setNumberOfArrows(heroNumberInitialization(board));

        char action = 'X';

        boolean heroWin = false;

        while (!heroWin && (Character.toUpperCase(action) != 'Q')) {
            Menu.printGamePlayMenu();
            action = scanner.next().charAt(0);

            switch (Character.toUpperCase(action)) {
                case 'L':
                    playerScore--;
                    moveHero();
                    printHeroData();
                    printBoard();
                    break;
                case 'B':
                    playerScore--;
                    turnHeroLeft();
                    printHeroData();
                    break;
                case 'J':
                    playerScore--;
                    turnHeroRight();
                    printHeroData();
                    break;
                case 'S':
                    shootArrow.shootArrow();
                    break;
                case 'H':
                    //dbGameSave();
                    //jsonSave();
                    xmlSave();
                    break;
                case 'Q':
                    System.out.println("Játék vége.");
                    break;
                default:
                    System.out.println("Érvénytelen akció. Kérem, válasszon újra.");
            }
        }
    }

    private void xmlSave() throws XMLStreamException, IOException {
        XmlGameSaver xmlGameSaver = new XmlGameSaver();
        xmlGameSaver.saveGame(player, createSaveString(), playerScore);
    }

    private void jsonSave() throws IOException {
        JsonGameSaver jsonGameSaver = new JsonGameSaver();
        jsonGameSaver.saveGame(player, createSaveString(), playerScore);
    }

    private void dbGameSave() throws SQLException {
        String save = createSaveString();

        GameQuery gameQuery = new GameQuery();
        gameQuery.saveGame(player, save, playerScore);
    }

    private String createSaveString() {
        String save = "";
        save += board.getSizeOfBoard() + " ";
        save += (char) ('A' + hero.getHeroColumn()) + " " + hero.getHeroRow() + " " + hero.getHeroDirection();
        for (int i = 0; i < board.getSizeOfBoard(); i++) {
            save += " ";
            for (int j = 0; j < board.getSizeOfBoard(); j++) {
                save += board.getBoard()[i][j];
            }
        }
        return save;
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
                playerScore += VALUE_OF_GOLD;
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
                playerScore += VALUE_OF_GOLD;
                System.out.println("TOP SCORE: " + playerScore);
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
                playerScore += VALUE_OF_GOLD;
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
                playerScore += VALUE_OF_GOLD;
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
