import java.util.Scanner;

/**
 * the game will start from here
 */

public class Game {
    static Scanner scanner = new Scanner(System.in);

    /**
     * starting the game in the multi mode
     */
    public static void startMultiMode() {
        System.out.println("Starting MultiMode!");
        System.out.println("Human 1 stones are showed:" + '\u26AA');
        System.out.println("Human 2 stones are showed:" + '\u26AB');
        System.out.println("First it is asked you to enter the row and column of your stone then");
        System.out.println("Enter the rotation in this form :boardNumber(a number between 1 to 4) rotationDirection('A' for AntiClockWise and " +
                "C for ClockWise) \n" +
                "Enter NO for ineffectual rotation (if it is available) \n" +
                "for example :1 A or 2 C or NO\n");
        Board board = new Board();
        Player human1 = new Player(1);
        Player human2 = new Player(2);
        //isFinished tells us if the game is finished or not
        boolean isFinished = false;
        while (true) {
            while (true) {
                board.printBoard();
                System.out.println("Human 1 turn " + '\u26AA');
                System.out.print("Enter the row: ");
                int row = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter the column: ");
                int column = scanner.nextInt();
                scanner.nextLine();

                System.out.println();
                if (!human1.putDisc(row, column)) {
                    System.out.println("Can not put here, try another");
                    continue;
                }
                board.printBoard();
                // if the player won the game after putting a new stone
                if (board.checkWinner(1)) {
                    isFinished = true;
                    break;
                }
                while (true) {
                    System.out.println("Enter the rotation in the EXPLAINED form:");
                    if (!human1.rotate(scanner.nextLine())) {
                        System.out.println("You may entered the pattern incorrectly or \"NO\" for ineffectual rotation is not available.");
                        continue;
                    }
                    break;
                }
                break;
            }
            if (isFinished) {
                System.out.println("human 1 won the game!");
                break;
            }
            // if the player won the game after the rotation
            else if (board.checkWinner(1)) {
                System.out.println("human 1 won the game! \nhuman 1 did a good rotation!");
            }
            // if the player did a rotation that cause the opponent won the game
            else if (board.checkWinner(2)) {
                board.printBoard();
                System.out.println("human 2 won the game! \nhuman 1 did a bad rotation!");
                break;
            }

            while (true) {
                board.printBoard();
                System.out.println("Human 2 turn " + '\u26AB');
                System.out.print("Enter the row: ");
                int row = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter the column: ");
                int column = scanner.nextInt();
                scanner.nextLine();
                System.out.println();
                if (!human2.putDisc(row, column)) {
                    System.out.println("Can not put here, try another");
                    continue;
                }
                board.printBoard();
                //if the player won the game after putting a new stone
                if (board.checkWinner(2)) {
                    isFinished = true;
                    break;
                }
                while (true) {
                    System.out.println("Enter the rotation in the EXPLAINED form:");
                    if (!human2.rotate(scanner.nextLine())) {
                        System.out.println("You may entered the pattern incorrectly or \"NO\" for ineffectual rotation is not available.");
                        continue;
                    }
                    break;
                }
                break;
            }
            if (isFinished) {
                System.out.println("human 2 won the game!");
                break;
            }
            // if the player won the game with a good rotation
            else if (board.checkWinner(2)) {
                System.out.println("human 2 won the game! \nhuman 2 did a good rotation!");
            }
            // if the player did a rotation that cause the opponent won the game
            else if (board.checkWinner(1)) {
                board.printBoard();
                System.out.println("human 1 won the game! \nhuman 2 did a bad rotation!");
                break;
            }
        }
    }

    public static void main(String[] args) {
        startMultiMode();
    }
}
