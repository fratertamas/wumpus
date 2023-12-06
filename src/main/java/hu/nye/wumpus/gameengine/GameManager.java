package hu.nye.wumpus.gameengine;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import hu.nye.wumpus.database.TopScoreQuery;
import hu.nye.wumpus.io.FileLoader;
import hu.nye.wumpus.io.impl.BoardFileLoader;
import hu.nye.wumpus.io.impl.HeroFileLoader;
import hu.nye.wumpus.menu.Menu;
import hu.nye.wumpus.model.Board;
import hu.nye.wumpus.model.Hero;
import hu.nye.wumpus.model.Player;

import javax.xml.stream.XMLStreamException;

public class GameManager {

    private Hero hero;
    private Board board;

    public void startGame() throws IOException, SQLException, XMLStreamException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Kérem a felhasználónevet: ");
        Player player = new Player(scanner.nextLine());

        System.out.println("Hello " + player.getPlayerName() + " jó játékot!\n");

        boolean isRunning = true;
        String choice;

        while (isRunning) {
            Menu.printMainMenu();
            System.out.println("Válasszon egy lehetőséget (1-5):");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    FileLoader<Board> boardFileLoader = new BoardFileLoader();
                    FileLoader<Hero> heroFileLoader = new HeroFileLoader();

                    this.board = boardFileLoader.load("wumpuszinput.txt");
                    this.hero = heroFileLoader.load("wumpuszinput.txt");
                    break;

                case "2":
                    System.out.println("Adatbázisból betöltés (nincs implementálva)");
                    //loadFromDatabase();
                    break;

                case "3":
                    System.out.println("Top lista");
                    TopScoreQuery topScoreQuery = new TopScoreQuery();
                    topScoreQuery.getTopScore();
                    break;

                case "4":
                    Game game = new Game(board, hero, player);
                    game.playGame();
                    break;

                case "5":
                    isRunning = false;
                    System.exit(0);
                    break;

                default:
                    System.out.println("Hibás választás! Kérem, válasszon újra.");
            }
        }
    }
}