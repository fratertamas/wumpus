package hu.nye.wumpus.io.saver.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import hu.nye.wumpus.model.Player;
import hu.nye.wumpus.io.saver.GameSerializer;

public class XmlGameSaver implements GameSerializer {
    @Override
    public void saveGame(Player player, String save, int playerScore) throws IOException, XMLStreamException {
        XMLOutputFactory outputFactory = XMLOutputFactory.newFactory();
        XMLStreamWriter writer = outputFactory.createXMLStreamWriter(new FileOutputStream("gameSave.xml"));

        // Write XML document start
        writer.writeStartDocument();

        // Write root element
        writer.writeStartElement("gameSave");

        // Write player element
        writer.writeStartElement("player");
        writer.writeCharacters(player.getPlayerName());
        writer.writeEndElement();

        // Write saveData element
        writer.writeStartElement("saveData");
        writer.writeCharacters(save);
        writer.writeEndElement();

        // Write playerScore element
        writer.writeStartElement("playerScore");
        writer.writeCharacters(Integer.toString(playerScore));
        writer.writeEndElement();

        // Write root element end
        writer.writeEndElement();

        // Write XML document end
        writer.writeEndDocument();

        // Close the writer
        writer.close();
    }
}
