package hu.nye.wumpus.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileLoader {

    public void loadFromFile(String fileName){
        File file = new File("src/main/resources/"+fileName);
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Scanner scanner = new Scanner(inputStream);
        // A pálya mérete
        int size = scanner.nextInt();

        // A hős pozíciója és iránya
        int heroColumn = scanner.next().charAt(0) - 'A';
        int heroRow = scanner.nextInt();
        char heroDirection = scanner.next().charAt(0);

        // A pálya elemei
        char[][] field = new char[size][size];
        for (int i = 0; i < size; i++) {
            field[i] = scanner.next().toCharArray();
        }
    }
}
