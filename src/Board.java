public class Board {
    private static int[][] board;

    public Board() {
        board = new int[6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                board[i][j] = 0;
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (board[i][j] == 1)
                    System.out.print('\u26AA' + "        ");
                else if (board[i][j] == 2)
                    System.out.print('\u26AB' + "        ");
                else System.out.print('\u22C5' + "         ");
                if (j == 2)
                    System.out.print('\u23D0' + "         ");
            }
            if (i == 2) System.out.println("\n\n-----------------------------------------------------------\n");
            else System.out.println("\n\n");
        }
    }

    public boolean checkWinner(int playerNumber) {
        int counter = 0;
        //ofoghi
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (j + 1 > 5) break;
                if (board[i][j] == playerNumber && board[i][j] == board[i][j + 1]) {
                    counter++;
                }
            }
            if (counter >= 4) {
                return true;
            } else counter = 0;
        }
        //amodi
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 6; i++) {
                if (i + 1 > 5) break;
                if (board[i][j] == playerNumber && board[i][j] == board[i + 1][j]) {
                    counter++;
                }
            }
            if (counter >= 4) {
                return true;
            } else counter = 0;
        }
        if (board[0][0] == playerNumber && board[1][1] == playerNumber && board[2][2] == playerNumber && board[3][3] == playerNumber && board[4][4] == playerNumber)
            return true;
        else if (board[1][1] == playerNumber && board[2][2] == playerNumber && board[3][3] == playerNumber && board[4][4] == playerNumber && board[5][5] == playerNumber)
            return true;
        else if (board[1][0] == playerNumber && board[2][1] == playerNumber && board[3][2] == playerNumber && board[4][3] == playerNumber && board[5][4] == playerNumber)
            return true;
        else if (board[0][1] == playerNumber && board[1][2] == playerNumber && board[2][3] == playerNumber && board[3][4] == playerNumber && board[4][5] == playerNumber)
            return true;
        else if (board[5][0] == playerNumber && board[4][1] == playerNumber && board[3][2] == playerNumber && board[2][3] == playerNumber && board[1][4] == playerNumber)
            return true;
        else if (board[4][1] == playerNumber && board[3][2] == playerNumber && board[2][3] == playerNumber && board[1][4] == playerNumber && board[0][5] == playerNumber)
            return true;
        else if (board[4][0] == playerNumber && board[3][1] == playerNumber && board[2][2] == playerNumber && board[1][3] == playerNumber && board[0][4] == playerNumber)
            return true;
        else if (board[5][1] == playerNumber && board[4][2] == playerNumber && board[3][3] == playerNumber && board[2][4] == playerNumber && board[1][5] == playerNumber)
            return true;
        else
            return false;

    }

    public static int[][] getBoard() {
        return board;
    }
}
