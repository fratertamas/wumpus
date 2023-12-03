package hu.nye.wumpus.menu;

public class Menu {

    public static void printMainMenu() {
        System.out.println("Válasszon egy lehetőséget:");
        System.out.println("1. Pálya fájlból beolvasása");
        System.out.println("2. Adatbázisból betöltés");
        System.out.println("3. Top lista");
        System.out.println("4. Játék");
        System.out.println("5. Kilépés");
    }

    public static void printGamePlayMenu() {
        System.out.println("Válasszon akciót: \n" +
                "L - lépés a nézés irányába\n" +
                "B - fordulás balra\n" +
                "J - fordulás jobbra\n" +
                "S - lövés\n" +
                "Q - kilépés");
    }
}
