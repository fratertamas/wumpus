package hu.nye.wumpus;

import hu.nye.wumpus.model.Player;

import java.util.Scanner;

public class WumpusGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Player player;

        System.out.print("Kérem a felhasználónevet: ");
        player = new Player(scanner.nextLine());

        System.out.println("Hello " + player.getPlayerName() + " jó játékot!\n");
    }
}