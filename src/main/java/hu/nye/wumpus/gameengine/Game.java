package hu.nye.wumpus.gameengine;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import javax.xml.stream.XMLStreamException;

import hu.nye.wumpus.database.GameQuery;
import hu.nye.wumpus.menu.Menu;
import hu.nye.wumpus.model.Board;
import hu.nye.wumpus.model.Hero;
import hu.nye.wumpus.model.Player;
import hu.nye.wumpus.serialization.impl.JsonGameSaver;
import hu.nye.wumpus.serialization.impl.XmlGameSaver;
import hu.nye.wumpus.utils.DisplayUtils;

public class Game {
    private final DisplayUtils displayUtils;
    private Board board;
    private Hero hero;
    private ShootArrow shootArrow;
    private HandleArrowShoot handleArrowShot;
    private int playerScore;
    private static final int VALUE_OF_GOLD = 20;
    private Player player;
    private HeroMovementHandler heroMovementHandler;

    public Game(Board board, Hero hero, Player player) {
        this.board = board;
        this.hero = hero;
        this.handleArrowShot = new HandleArrowShoot(board, hero);
        this.shootArrow = new ShootArrow(board, hero, handleArrowShot);
        this.playerScore = 100;
        this.player = player;
        this.displayUtils = new DisplayUtils(board, hero);
        this.heroMovementHandler = new HeroMovementHandler(player, hero, board);
    }

    public void playGame() throws SQLException, IOException, XMLStreamException {

        Scanner scanner = new Scanner(System.in);

        hero.setNumberOfArrows(heroNumberOfArrowsInitialization(board));

        char action = 'X';

        boolean heroWin = false;

        while (!heroWin && (Character.toUpperCase(action) != 'Q')) {
            Menu.printGamePlayMenu();
            action = scanner.next().charAt(0);

            switch (Character.toUpperCase(action)) {
                case 'L':
                    playerScore--;
                    heroMovementHandler.moveHero(playerScore);
                    //moveHero();
                    displayUtils.printHeroData();
                    displayUtils.printBoard();
                    break;
                case 'B':
                    playerScore--;
                    turnHeroLeft();
                    displayUtils.printHeroData();
                    break;
                case 'J':
                    playerScore--;
                    turnHeroRight();
                    displayUtils.printHeroData();
                    break;
                case 'S':
                    shootArrow.shootArrow();
                    break;
                case 'H':
                    dbGameSave();
                    jsonSave();
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

    private int heroNumberOfArrowsInitialization(Board board) {
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
