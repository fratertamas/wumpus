package hu.nye.wumpus.model;

/**
 * Arrow.
 * Nyíl
 */
public class Arrow {
    private int arrowRow;
    private int arrowColumn;
    private char arrowDirection;

    public Arrow(int arrowRow, int arrowColumn, char arrowDirection) {
        this.arrowRow = arrowRow;
        this.arrowColumn = arrowColumn;
        this.arrowDirection = arrowDirection;
    }

    public int getArrowRow() {
        return arrowRow;
    }

    public int getArrowColumn() {
        return arrowColumn;
    }

    public char getArrowDirection() {
        return arrowDirection;
    }
}
