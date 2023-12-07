package hu.nye.wumpus.io.loader.impl;

import java.util.Scanner;

import hu.nye.wumpus.io.loader.FileLoader;
import hu.nye.wumpus.model.Board;

/**
 * BoardDBLoader.
 * Pálya betöltés db-ből
 */
public class BoardDBLoader implements FileLoader {
    @Override
    public Board load(String gameState) {
        Scanner scanner = new Scanner(gameState);
        Board board = new Board();

        //Pálya mérete
        int sizeOfBoard = scanner.nextInt();
        board.setSizeOfBoard(sizeOfBoard);

        //A hős poziciója és iránya
        int heroColumn = scanner.next().charAt(0) - 'A';
        int heroRow = scanner.nextInt();
        char heroDirection = scanner.next().charAt(0);

        //A pálya elemei
        createBoard(board, board.getSizeOfBoard(), scanner);

        scanner.close();

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
