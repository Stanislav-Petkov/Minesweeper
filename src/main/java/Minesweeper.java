import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class generates the board of the game and its functionality
 */
class Minesweeper {

    private ConsolePrinter printer;
    private ConsoleReader reader;
    private boolean lost;
    private int inputRow;
    private int inputCol;
    private final int size;
    private Field[][] board;

    Minesweeper(int size, int numberOfMines) {
        this.size = size;
        this.reader = new ConsoleReader();
        this.printer = new ConsolePrinter();
        showEmptyBoard();
        initBord(numberOfMines);
        calculateNeighbors();
        startTheGame();
    }

    //Shows and empty the board, when the game starts
    void showEmptyBoard() {
        printer.printCurrentStatusMessage();
        System.out.print("  ");
        for (int i = 1; i <= size; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 1; i <= size; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < size; j++) {
                System.out.print("- ");
            }
            System.out.println();
        }
        getRowAndColValues();
    }

    // Gets the input for the row and column coordinates from the reader object
    private void getRowAndColValues() {
        reader.readRowAndCow();
        inputRow = reader.getInputRow();
        inputCol = reader.getInputCol();
    }

    // Initializes the board with field with values only once at the beginning
    private void initBord(int numberOfMines) {
        List<Field> mines = new ArrayList<Field>();
        // Subtracts 1, because the first input should be valid
        for (int i = 0; i < (size * size) - 1; i++) {
            mines.add(new Field(i < numberOfMines));
        }

        Collections.shuffle(mines);

        board = new Field[size + 2][size + 2];
        for (int i = 0; i < size + 2; i++) {
            for (int j = 0; j < size + 2; j++) {
                // First row,col and last row, col are always without mines
                // This check : ( i == inputRow && j == inputCol)
                // makes sure that the first move of the player is always safe and not a mine!
                if ((i == 0 || j == 0 || i == size + 1 || j == size + 1 || (i == inputRow && j == inputCol))) {
                    board[i][j] = new Field(false);
                } else {
                    board[i][j] = mines.remove(0);
                }
            }
        }
        System.out.println();
    }

    //Calculates the neighbors of each cell
    private void calculateNeighbors() {
        // Skips the 0-th row and the last row
        for (int i = 1; i <= size; i++) {
            // Skips the 0-th column and last column
            for (int j = 1; j <= size; j++) {
                int count = 0;
                // Checks if any of the 8 neighbors has a mine
                for (int di = -1; di <= 1; di++) {
                    for (int dj = -1; dj <= 1; dj++) {
                        if (di != 0 || dj != 0) {
                            if (board[i + di][j + dj].mine) {
                                count++;
                            }
                        }
                    }
                }
                board[i][j].neighbors = count;
            }
        }
    }

    // If the input for the row and column are such that the field object contains a mine, the game stops
    private boolean checkIfGameIsOver() {
        Field f = board[inputRow][inputCol];
        if (f.mine) {
            return true;
        }
        return false;
    }

    private void printRevealedCellsWhenTheGameIsOver() {
        System.out.print("  ");
        for (int i = 1; i <= size; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 1; i <= size; i++) {
            System.out.print(i + " ");
            for (int j = 1; j <= size; j++) {
                Field f = board[i][j];
                if (f.mine) {
                    System.out.print("* ");
                } else {
                    //if it is revealed
                    if (!f.hidden) {
                        if (f.neighbors == 0) {
                            System.out.print("  ");
                        } else {
                            System.out.print(f.neighbors + " ");
                        }
                    } else {
                        System.out.print("- ");
                    }
                }
            }
            System.out.println();
        }
        printer.printYouLostMessage();
    }

    private void startTheGame() {
        //If the user steps on a mine, he loses the game
        while (!lost) {
            reveal(inputRow, inputCol);
            showBoard();
        }
    }

    boolean isOutOfBounds(int row, int col) {
        return row <= 0 || col <= 0 || row >= board.length - 1 || col >= board[row].length - 1;
    }

    void reveal(int row, int col) {
        // Returns if the current coordinates are out of the game board coordinates
        if (isOutOfBounds(row, col)) {
            return;
        }

        // Returns if the current cell is revealed
        if (!board[row][col].hidden) {
            return;
        }
        board[row][col].hidden = false;

        // Returns when the current cell neighbors are more than 0
        if (board[row][col].neighbors != 0) {
            return;
        }

        // Go recursively in all directions
        reveal(row - 1, col - 1);
        reveal(row - 1, col + 1);
        reveal(row + 1, col - 1);
        reveal(row + 1, col + 1);
        reveal(row - 1, col);
        reveal(row + 1, col);
        reveal(row, col - 1);
        reveal(row, col + 1);
    }

    // Shows the board of the game
    private void showBoard() {
        printer.printCurrentStatusMessage();
        if (checkIfGameIsOver()) {
            printRevealedCellsWhenTheGameIsOver();
            lost = true;
            // The game stops
            return;
        }

        System.out.print("  ");
        for (int i = 1; i <= size; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 1; i <= size; i++) {
            System.out.print(i + " ");
            for (int j = 1; j <= size; j++) {
                Field f = board[i][j];
                if (i == inputRow && j == inputCol && f.neighbors == 0) {
                    f.hidden = false;
                } else if (i == inputRow && j == inputCol && f.neighbors > 0) {
                    f.hidden = false;
                }
                if (!f.hidden && f.neighbors == 0) {
                    System.out.print("  ");
                } else if (!f.hidden && f.neighbors > 0) {
                    System.out.print(f.neighbors + " ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }

        // Read another pair of input row and input column
        getRowAndColValues();
    }
}

