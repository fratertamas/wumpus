package hu.nye.wumpus;

import hu.nye.wumpus.menu.Menu;
import hu.nye.wumpus.model.Player;

import java.util.Scanner;

public class WumpusGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Player player;

        System.out.print("Kérem a felhasználónevet: ");
        player = new Player(scanner.nextLine());

        System.out.println("Hello " + player.getPlayerName() + " jó játékot!\n");

        boolean isRunning = true;

        while (isRunning) {
            Menu.printMainMenu();
            System.out.println("Válasszon egy lehetőséget (1-5):");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    //loadFromFile();
                    System.out.println("Pályabetöltés...");
                    break;
                case "2":
                    System.out.println("DB betöltés....");
                    //loadFromDatabase();
                    break;
                case "3":
                    System.out.println("Top lista....");
                    //highScore();
                    break;
                case "4":
                    System.out.println("Játék.....");
                    //playGame();
                    break;
                case "5":
                    System.out.println("Kilépés...");
                    isRunning = false;
                    System.exit(0);
                    break;
                default:
                    System.out.println("Érvénytelen választás. Kérem, válasszon újra.");
            }
        }
    }
}