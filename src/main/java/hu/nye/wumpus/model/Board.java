package hu.nye.wumpus.model;

public class Board {
    private int sizeOfBoard;
    private char[][] board;

    public int getSizeOfBoard() {
        return sizeOfBoard;
    }

    public void setSizeOfBoard(int sizeOfBoard) {
        this.sizeOfBoard = sizeOfBoard;
    }

    public void setBoard(char[][] field) {
        this.board = field;
    }

    public char[][] getBoard(){
        return board;
    }
}
