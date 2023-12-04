package hu.nye.wumpus.data;

import hu.nye.wumpus.model.Board;
import hu.nye.wumpus.model.Hero;
import hu.nye.wumpus.service.Game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileLoader {

    public Board loadFromFile(String fileName) {
        File file = new File("src/main/resources/" + fileName);
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Scanner scanner = new Scanner(inputStream);

        Board board = new Board();

        //Pálya mérete
        board.setSizeOfBoard(scanner.nextInt());

        //A hős poziciója és iránya
        int heroColumn = scanner.next().charAt(0) - 'A';
        int heroRow = scanner.nextInt();
        char heroDirection = scanner.next().charAt(0);

        // Create the hero object


        //A pálya elemei
        createBoard(board, board.getSizeOfBoard(), scanner);



        System.out.println("Pálya elemei:");
        int numberOfArrows = 0;
        for (int i = 0; i < board.getSizeOfBoard(); i++) {
            for (int j = 0; j < board.getSizeOfBoard(); j++) {
                System.out.print(board.getBoard()[i][j]);
                if (board.getBoard()[i][j] == 'U') {
                    numberOfArrows++;
                }
            }
            System.out.println();
        }

        Hero hero = new Hero(heroColumn, heroRow, heroDirection, false, numberOfArrows);

        hero.setNumberOfArrows(numberOfArrows);


        System.out.println("Pálya mérete: " + board.getSizeOfBoard());
        System.out.println("Hős pozíciója: " + (char) ('A' + hero.getHeroColumn()) + " " + hero.getHeroRow());
        System.out.println("Hős iránya: " + hero.getHeroDirection());
        System.out.println("Hős nyílainak száma: " + hero.getNumberOfArrows());


        // Pass the hero object to the Game class
        Game game = new Game(board, hero);
        return board;
    }
    private void createBoard(Board board, int sizeOfBoard, Scanner scanner) {
        char[][] field = new char[sizeOfBoard][sizeOfBoard];
        for (int i = 0; i < sizeOfBoard; i++) {
            field[i] = scanner.next().toCharArray();
        }
        board.setBoard(field);
    }

}