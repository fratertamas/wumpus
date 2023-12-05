package hu.nye.wumpus.io;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileLoader<T> {
    T load(String fileName) throws FileNotFoundException, IOException;
}
