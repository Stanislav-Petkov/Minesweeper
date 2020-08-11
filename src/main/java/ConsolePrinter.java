/**
 * This class is used to print the output messages on the console
 */
public class ConsolePrinter {

    public ConsolePrinter() {

    }

    public void printInitialMessages() {
        System.out.println("Enter the Difficulty Level");
        System.out.println("Press 0 for BEGINNER (9 * 9 Cells and 10 Mines)");
        System.out.println("Press 1 for INTERMEDIATE (16 * 16 Cells and 40 Mines)");
        System.out.println("Press 2 for ADVANCED (24 * 24 Cells and 99 Mines)");
    }

    public void printYouLostMessage() {
        System.out.println("You lost!");
    }

    public void printCurrentStatusMessage(){
        System.out.println("Current Status of Board :");
    }

    public void printEnterYourMoveMessage(){
        System.out.println();
        System.out.println("Enter your move, (row, column)");
        System.out.print("-> ");
    }
}
