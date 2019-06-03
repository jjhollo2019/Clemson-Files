/* Jeremy Holloway
 * CPSC-2150-001
 * Homework #3
 * 10/28/2018
 */
package cpsc2150.connectX;

/**
 * Function: GameBoard class
 * Description: This function holds the class functions for the GameBoard
 * @invariant row >= 3 && row <= 100, column >= 3 && column <= 100, winNum >= 3 && winNum <= 25
 */
public class GameBoard implements IGameBoard {
    private static char board[][];
    private static int row;
    private static int column;
    private static int winNum;

    /**
     * Function: GameBoard()
     * Description: This function will initialize the game board
     * Input: none
     * Output: none
     * @pre row >= 3 && row <= 100, column >= 3 && column <= 100, winNum >= 3 && winNum <= 25
     * @post board[][] = board[row][column]
     */
    public GameBoard(int rows, int columns, int win)
    {
        board = new char[rows][columns];
        row = rows;
        column = columns;
        for(int r = 0; r < row; r++){
            for(int c = 0; c < column; c++){
                board[r][c] = ' ';
            }
        }
        winNum = win;
    }

    public void placeToken(char p, int c){
        //find a free space in the column
        for (int a = (row - 1); a >= 0; a--) {
            //check for free space
            if (board[a][c] == ' ') {
                board[a][c] = p;//place char
                return;
            }
        }
    }

    public char whatsAtPos(int r, int c){
        return board[r][c];
    }

    /**
     * Function: toString()
     * Description: This function will print the game board
     * Input: none
     * Output: prints game board
     * @pre board[][] == board[row][column]
     * @post board is converted into a string
     * @return the string is returned to the user
     */
    @Override
    public String toString(){
        String board = "|";
        for (int a = 0; a < column; a++){
            if (a < 10){
                board += " " + a + "|";
            }
            else
                board += a + "|";
        }
        board += "\n";
        for (int a = 0; a < row; a++) {
            for (int b = 0; b < column; b++) {
                //add space if there is no char
                if (whatsAtPos(a, b) == ' ') {
                    board += "|  ";
                } else//add char if present
                    board += ("|" + whatsAtPos(a, b) + " ");
            }
            board += ("|\n");//add closing end bracket and new line char
        }
        return board;//return the finished string
    }

    public int getNumRows(){
        return row;
    }

    public int getNumColumns(){
        return column;
    }

    public int getNumToWin(){
        return winNum;
    }
}
