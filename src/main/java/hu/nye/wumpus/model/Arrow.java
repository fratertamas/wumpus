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

    public void setArrowRow(int arrowRow) {
        this.arrowRow = arrowRow;
    }

    public void setArrowColumn(int arrowColumn) {
        this.arrowColumn = arrowColumn;
    }

    public void setArrowDirection(char arrowDirection) {
        this.arrowDirection = arrowDirection;
    }

    public int getArrowColumn() {
        return arrowColumn;
    }

    public char getArrowDirection() {
        return arrowDirection;
    }
}
