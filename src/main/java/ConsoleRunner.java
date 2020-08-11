/**
 * This class is ues to initialize a printer,reader and minesweeper objects
 */
public class ConsoleRunner {

    private ConsolePrinter printer;
    private ConsoleReader reader;

    public ConsoleRunner() {
        printer = new ConsolePrinter();
        reader = new ConsoleReader();
    }

    public void run() {
        printer.printInitialMessages();
        reader.readGameLevelInput();

        int matrixSize = reader.getMatrixSize();
        int numberOfMines = reader.getNumberOfMines();

        // Create a new minesweeper object
        Minesweeper minesweeper = new Minesweeper(matrixSize, numberOfMines);
    }
}
