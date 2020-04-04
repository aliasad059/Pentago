/**
 * the pentago game needs player ,in this class we simulate this
 */
public class Player {
    int type;
    private int[][] board;
    private int boardToRotate;
    private char rotateDirection;

    public Player(int type) {
        this.type = type;
    }

    /**
     * put a stone/disc in the board
     * @param row
     * @param column
     * @return
     */
    public boolean putDisc(int row, int column) {
        board = Board.getBoard();
        if (row > 5 || row < 0 || column > 5 || column < 0)
            return false;
        if (board[row][column] == 0) {
            board[row][column] = this.type;
            return true;
        } else return false;
    }

    /**
     * rotate the entered part of board
     * @param rotate entered rotation
     * @return false if the entered rotation text is not as it has to be (not in form of explained form)
     */
    public boolean rotate(String rotate) {
        try {
            if (rotate.toLowerCase().equals("no")) {
                return checkMayNotRotate();
            }
            boardToRotate = (int) rotate.charAt(0) - 48;
            if (boardToRotate < 1 || boardToRotate > 4) {
                return false;
            }
            rotateDirection = rotate.charAt(2);

            if (rotateDirection != 'A' && rotateDirection != 'C') {
                return false;
            }
        }
        catch (StringIndexOutOfBoundsException e){
            return false;
        }


        int[][] temp = makeTempArray(boardToRotate);

        if (rotateDirection == 'A') {
            changeBoard(boardToRotate, rotateAntiClockWise(temp));
            return true;
        } else if (rotateDirection == 'C') {
            changeBoard(boardToRotate, rotateClockWise(temp));
            return true;
        }
        return false;
    }

    /**
     * copy the entered part of the board to the temp array. if there is no such a this, the changes will apply to the board directly and makes mistakes
     * @param boardToRotate part of board that will be rotated
     * @return
     */
    private int[][] makeTempArray(int boardToRotate) {

        int[][] temp = new int[3][3];

        if (boardToRotate == 1) {
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    temp[i][j] = board[i][j];
        } else if (boardToRotate == 2) {
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    temp[i][j] = board[i][j + 3];
        } else if (boardToRotate == 3) {
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    temp[i][j] = board[i + 3][j];
        } else if (boardToRotate == 4) {
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    temp[i][j] = board[i + 3][j + 3];
        }
        return temp;
    }

    /**
     * anti clockwise rotation means rotate the part 3 times clockwise
     * @param temp the array that is made from the makeTempArray method
     * @return rotated array
     */
    private int[][] rotateAntiClockWise(int[][] temp) {
        return rotateClockWise(rotateClockWise(rotateClockWise(temp)));
    }
    /**
     * it actually arrange the stones in a row to stones in a column
     * @param temp the array that is made from the makeTempArray method
     * @return rotated array
     */
    private int[][] rotateClockWise(int[][] temp) {

        int[][] rotatedArray = new int[3][3];
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                rotatedArray[column][2 - row] = temp[row][column];
            }
        }
        return rotatedArray;
    }

    /**
     * check if the player is able to do a ineffectual rotation
     * @return true if the player is able
     */
    private boolean checkMayNotRotate() {
        boolean[] mustRotate = new boolean[4];
        for (int i = 0; i < 4; i++)
            mustRotate[i] = true;


        for (int i = 1; i <= 4; i++) {

            int[][] temp = makeTempArray(i);
            int[][] rotatedArray = rotateClockWise(temp);

            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (temp[j][k] != rotatedArray[j][k]) {
                        mustRotate[i - 1] = false;
                    }
                }
            }
        }
        return mustRotate[0] || mustRotate[1] || mustRotate[2] || mustRotate[3];
    }

    /**
     * apply the changes to the board
     * @param boardToChange board to change number
     * @param rotatedArray the rotated part of board
     */
    private void changeBoard(int boardToChange, int[][] rotatedArray) {
        if (boardToChange == 1) {
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    board[i][j] = rotatedArray[i][j];
        } else if (boardToChange == 2) {
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    board[i][j + 3] = rotatedArray[i][j];
        } else if (boardToChange == 3) {
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    board[i + 3][j] = rotatedArray[i][j];
        } else if (boardToChange == 4) {
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    board[i + 3][j + 3] = rotatedArray[i][j];
        }

    }


}
