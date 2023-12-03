package hu.nye.wumpus.model;

public class Hero {
    private int heroColumn;
    private int heroRow;
    private char heroDirection;
    private boolean hasGold;
    private int numberOfArrows;

    public Hero(int heroColumn, int heroRow, char heroDirection, boolean hasGold, int numberOfArrows) {
        this.heroColumn = heroColumn;
        this.heroRow = heroRow;
        this.heroDirection = heroDirection;
        this.hasGold = hasGold;
        this.numberOfArrows = numberOfArrows;
    }

    public Hero() {

    }

    public int getHeroColumn() {
        return heroColumn;
    }

    public void setHeroColumn(int heroColumn) {
        this.heroColumn = heroColumn;
    }

    public int getHeroRow() {
        return heroRow;
    }

    public void setHeroRow(int heroRow) {
        this.heroRow = heroRow;
    }

    public char getHeroDirection() {
        return heroDirection;
    }

    public void setHeroDirection(char heroDirection) {
        this.heroDirection = heroDirection;
    }

    public boolean isHasGold() {
        return hasGold;
    }

    public void setHasGold(boolean hasGold) {
        this.hasGold = hasGold;
    }

    public int getNumberOfArrows() {
        return numberOfArrows;
    }

    public void setNumberOfArrows(int numberOfArrows) {
        this.numberOfArrows = numberOfArrows;
    }
}
