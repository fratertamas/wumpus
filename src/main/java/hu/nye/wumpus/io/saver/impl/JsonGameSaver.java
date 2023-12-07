package hu.nye.wumpus.io.saver.impl;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import hu.nye.wumpus.io.saver.GameSaver;
import hu.nye.wumpus.model.GameSave;
import hu.nye.wumpus.model.Player;

/**
 * JSONGameSaver.
 * Mentés JSON-ba
 */
public class JsonGameSaver implements GameSaver {
    @Override
    public void saveGame(Player player, String save, int playerScore) throws IOException {
        String jsonSave = convertToJson(player, save, playerScore);

        try (FileWriter writer = new FileWriter("gameSave.json")) {
            writer.write(jsonSave);
        }
    }

    private String convertToJson(Player player, String save, int playerScore) {
        Gson gson = new Gson();

        GameSave gameSave = new GameSave(player.getPlayerName(), save, playerScore);

        return gson.toJson(gameSave);
    }

}
