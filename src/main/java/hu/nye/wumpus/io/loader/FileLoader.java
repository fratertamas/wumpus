package hu.nye.wumpus.io.loader;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * FileLoader.
 * interface
 */
public interface FileLoader<T> {
    T load(String fileName) throws IOException;
}
