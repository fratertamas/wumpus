package hu.nye.wumpus.serialization.impl;

import com.google.gson.Gson;
import hu.nye.wumpus.model.GameSave;
import hu.nye.wumpus.model.Player;
import hu.nye.wumpus.serialization.GameSerializer;

import java.io.FileWriter;
import java.io.IOException;

public class JsonGameSaver implements GameSerializer {
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
