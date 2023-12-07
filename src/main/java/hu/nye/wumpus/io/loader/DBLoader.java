package hu.nye.wumpus.io.loader;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import javax.xml.stream.XMLStreamException;

import hu.nye.wumpus.database.GameQuery;

public class DBLoader {

    public void loadDataFromDatabase() throws SQLException, XMLStreamException, IOException {
        GameQuery gameQuery = new GameQuery();
        gameQuery.listSavedGames();

        Scanner scanner = new Scanner(System.in);

        System.out.print("\nKérem adja meg a betöltendő mentés GameId oszlopában található értéket: ");
        String gameId = scanner.nextLine();

        gameQuery.loadGame(gameId);
    }
}
