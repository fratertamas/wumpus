package hu.nye.wumpus.gameengine;

import hu.nye.wumpus.model.Board;
import hu.nye.wumpus.model.Hero;
import hu.nye.wumpus.model.Player;

import java.sql.SQLException;

public class HeroMovementHandler {
    private Board board;
    private Hero hero;
    private Player player;

    public HeroMovementHandler(Player player, Hero hero, Board board) {
        this.board = board;
        this.hero = hero;
        this.player = player;
    }

    public void moveHero(int playerScore) throws SQLException {
        int newRow = hero.getHeroRow();
        int newColumn = hero.getHeroColumn();

        char direction = hero.getHeroDirection();
        char targetCell = getTargetCell(direction);
        System.out.println("TargetCell: " + targetCell);

        if (targetCell == 'W') {
            System.out.println("Fal jön, oda nem léphetsz!");
        } else if (targetCell == 'P') {
            handleP(targetCell, direction);
        } else if (targetCell == 'U') {
            System.out.println("A WUMPUS megölt, vége a játéknak!");
        } else if (targetCell == 'G') {
            handleGold();
        } else {
            handleMove(direction);
        }

        GameStatusChecker gameStatusChecker = new GameStatusChecker();
        gameStatusChecker.checkGameStatus(player, hero, playerScore);
    }

    private void newHeroPozition(int row, int column) {
        hero.setHeroRow(hero.getHeroRow() + row);
        hero.setHeroColumn(hero.getHeroColumn() + column);
    }

    private char getTargetCell(char direction) {
        int rowChange = 0;
        int columnChange = 0;

        switch (direction) {
            case 'E':
                rowChange = -1;
                columnChange = 1;
                break;
            case 'N':
                rowChange = -2;
                break;
            case 'W':
                rowChange = -1;
                columnChange = -1;
                break;
            case 'S':
                rowChange = 0;//-1;
                break;
        }
        int targetRow = hero.getHeroRow() + rowChange;
        int targetColumn = hero.getHeroColumn() + columnChange;

        return board.getBoard()[targetRow][targetColumn];
    }

    private void handleMove(char direction) {
        int rowChange = 0;
        int columnChange = 0;

        switch (direction) {
            case 'E':
                columnChange = 1;
                break;
            case 'N':
                rowChange = -1;
                break;
            case 'W':
                columnChange = -1;
                break;
            case 'S':
                rowChange = 1;
                break;
        }

        hero.setHeroRow(hero.getHeroRow() + rowChange);
        hero.setHeroColumn(hero.getHeroColumn() + columnChange);
    }

    private void handleGold() {
        hero.setHasGold(true);
    }

    private void handleP(char targetCell, char direction) {
        if (hero.getNumberOfArrows() > 0) {
            hero.setNumberOfArrows(hero.getNumberOfArrows() - 1);
            if (direction == 'E') {
                newHeroPozition(0, 1);
            } else if (direction == 'W') {
                newHeroPozition(0, -1);
            } else if (direction == 'N') {
                newHeroPozition(-1, 0);
            } else {
                newHeroPozition(1,0);
            }
            System.out.println("Verembe léptél, elvesztettél egy nyilat!");
        } else {
            System.out.println("Nincs több nyilad! Vesztettél! Vége a játéknak!");
        }
    }


}
