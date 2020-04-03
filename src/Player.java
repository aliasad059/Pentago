
public class Player {
    int type;
    private int[][] board;
    private int boardToRotate;
    private char rotateDirection;

    public Player(int type) {
        this.type = type;
    }

    public boolean putDisc(int row, int column) {
        board = Board.getBoard();
        if (row > 5 || row < 0 || column > 5 || column < 0)
            return false;
        if (board[row][column] == 0) {
            board[row][column] = this.type;
            return true;
        } else return false;
    }

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

    private int[][] rotateAntiClockWise(int[][] temp) {
        return rotateClockWise(rotateClockWise(rotateClockWise(temp)));
    }

    private int[][] rotateClockWise(int[][] temp) {

        int[][] rotatedArray = new int[3][3];
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                rotatedArray[column][2 - row] = temp[row][column];
            }
        }
        return rotatedArray;
    }

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
