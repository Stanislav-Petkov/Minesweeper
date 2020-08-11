import java.util.Scanner;

/**
 * This class is used to read the input of the user from the console
 */
public class ConsoleReader {
    private ConsolePrinter printer;
    private Scanner scanner;
    private int gameLevel;
    private int numberOfMines;
    private int matrixSize;

    private int inputRow;
    private int inputCol;

    public ConsoleReader() {
        scanner = new Scanner(System.in);
        printer = new ConsolePrinter();
    }

    public void readGameLevelInput() {

        String input = scanner.nextLine();

        while (!input.equals("0") && !input.equals("1") && !input.equals("2")) {
            System.out.println("Wrong input, try again:");
            input = scanner.nextLine();
        }

        if (input.equals("0")) {
            matrixSize = 9;
            numberOfMines = 10;
        } else if (input.equals("1")) {
            matrixSize = 16;
            numberOfMines = 40;
        } else if (input.equals("2")) {
            matrixSize = 24;
            numberOfMines = 99;
        }

        gameLevel = Integer.parseInt(input);
    }

    public int getGameLevel() {
        return gameLevel;
    }

    public int getNumberOfMines() {
        return numberOfMines;
    }

    public int getMatrixSize() {
        return matrixSize;
    }

    public void setMatrixSize(int matrixSize) {
        this.matrixSize = matrixSize;
    }

    public void readRowAndCow() {
        printer.printEnterYourMoveMessage();
        String[] inputValue = scanner.nextLine().split("\\s+");
        inputRow = Integer.parseInt(inputValue[0]);
        inputCol = Integer.parseInt(inputValue[1]);
    }

    public int getInputRow() {
        return inputRow;
    }

    public void setInputRow(int inputRow) {
        this.inputRow = inputRow;
    }

    public int getInputCol() {
        return inputCol;
    }

    public void setInputCol(int inputCol) {
        this.inputCol = inputCol;
    }
}
