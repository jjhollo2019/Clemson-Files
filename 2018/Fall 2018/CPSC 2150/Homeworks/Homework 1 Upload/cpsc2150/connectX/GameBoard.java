/* Jeremy Holloway
 * CPSC-2150-001
 * Homework #1
 * 9/23/2018
 */
package cpsc2150.connectX;

public class GameBoard {
    private static char board[][];
    private static int rows = 6;
    private static int columns = 7;
    private static int win = 4;
    /**
     * Function: GameBoard()
     * Description: This function will initialize the game board
     * Input: none
     * Output: none
     * @invariant board must be declared before calling constructor
     * @pre must be called from the main
     * @post object = char[6][7]
     */
    public GameBoard() {
        board = new char[6][7];
    }//initialize board

    /**
     * Function: checkIfFree(int c)
     * Description: This function will determine if the row is free
     * Intput: int c
     * Output: true/false
     * @param c is the user selected column to place a token
     * @pre user must verify input 0 >= c < 7, user must call constructor before using this function
     * @post user will get feedback if column is full or free
     * @return true if column is free / false if column is full
     */
    public static boolean checkIfFree(int c){
        for(int a = 0; a < 6; a++){//loop through the rows of the selected column
            //return true if a free space is found
            if( board[a][c] != 'X' && board[a][c] != 'O'){
                return true;
            }
        }
        //default to false if no empty space is found
        return false;
    }

    /**
     * Function: checkForWin(int c)
     * Description: This function will check several other functions to determine a win
     * Input: int c
     * Output: true/false
     * @param c is the user selected column to check
     * @pre user must verify 0 <= c < 7, user must call constructor before using this function
     * @post user will get feedback if they've won
     * @return true if any condition is met / false if none are met
     */
    public static boolean checkForWin(int c){
        if (checkHorizWin(0, c, 'X') == true){//check horizontally for X
            return true;
        }
        if (checkHorizWin(0, c, 'O') == true){//check horizontally for O
            return true;
        }
        if (checkVertWin(0, c, 'X') == true){//check vertically for X
            return true;
        }
        if (checkVertWin(0, c, 'O') == true){//check vertically for O
            return true;
        }
        if (checkDiagWin(0, c, 'X') == true){//check diagonally for X
            return true;
        }
        if (checkDiagWin(0, c, 'O') == true){//check diagonally for O
            return true;
        }
        return false;//default to false
    }

    /**
     * Function: placeToken(char p, int c)
     * Description: This function will place a char in a board column
     * Input: char p, int c
     * Output: none
     * @param p is the player token to place
     * @param c is the player selected column
     * @pre user must verify 0 <= c < 7, user must call constructor before using this function
     * @post token will be in selected column if free
     */
    public static void placeToken(char p, int c){
        if (checkIfFree(c) == false){//check if column is free
            return;//return if not
        }
        else {
            //find a free space in the column
            for (int a = 5; a >= 0; a--) {
                //check for free space
                if (board[a][c] != 'X' && board[a][c] != 'O') {
                    board[a][c] = p;//place char
                    return;
                }
            }
        }
    }

    /**
     * Function: checkHorizWin(int r, int c, char p)
     * Description: This function checks for a row win
     * Input: int r, int c, char p
     * Output: true/false
     * @param r is the row to check
     * @param c is the column to check
     * @param p is the char to check for
     * @invariant 0 <= r < 6, 0 <= c < 7
     * @pre user must call constructor before using this function, user must verify input
     * @post checkHorizWin == true or false
     * @return true if there is a horizontal win, else return false
     */
    public static boolean checkHorizWin(int r, int c, char p){
        int winCount = 0;//declare a char counter
        //loop through the board
        for (r = 0; r < rows; r++) {
            for (c = 0; c < columns; c++) {
                if (board[r][c] == p){//increment counter if position equals p
                    winCount++;
                }
                else{//reset to zero if p is not sequential
                    winCount = 0;
                }
                if (winCount >= win) {//return true if there are at least 4 p's
                    return true;
                }
            }
        }
        return false;//default to false
    }

    /**
     * Function: checkVertWin(int r, int c, char p)
     * Description: This function will check for a vertical win
     * Input: int r, int c, char p
     * Output: true/false
     * @param r is the row to check
     * @param c is the column to check
     * @param p is the char to check for
     * @invariant 0 <= r < 6, 0 <= c < 7, p == 'X' or 'O'
     * @pre user must call constructor before using this function, user must verify correct input
     * @post checkVertWin == true or false
     */
    public static boolean checkVertWin(int r, int c, char p) {
        int winCount = 0;//declare a char counter
        //loop through the board to determine a win
        for (c = 0; c < columns; c++){
            for (r = 0; r < rows; r++){
                if (board[r][c] == p){//count if position equals p
                    winCount++;
                }
                else{//reset if position does not equal p
                    winCount = 0;
                }
                if (winCount >= win){//return true if there are four in a row
                    return true;
                }
            }
        }
        return false;//default to false
    }

    /**
     * Function: checkDiagWin(int r, int c, char p)
     * Description: This function will check for a diagonal win
     * Input: int r, int c, char p
     * Output: true/false
     * @param r is the row to check
     * @param c is the column to check
     * @param p is the char to check for
     * @invariant 0 <= r < 6, 0 <= c < 7, p == 'X' or 'O'
     * @pre user must call constructor before using this function, user must verify correct input
     * @post checkDiagWin == true or false
     */
    public static boolean checkDiagWin(int r, int c, char p){
        //check forward diagonal win
        for (r = 0; r < rows - 3; r++) {//loop through rows
            for (c = 0; c < columns - 3; c++) {//loop through columns
                if (board[r][c] == p &&//check if there a four in a row
                        board[r + 1][c + 1] == p &&
                        board[r + 2][c + 2] == p &&
                        board[r + 3][c + 3] == p) {
                    return true;//return true if there are four in a row
                }
            }
        }
        //check backwards diagonal win
        for (r = 0; r < rows - 3; r++) {//loop through the rows
            for (c = 3; c < columns; c++) {//loop through the columns
                if (board[r][c] == p &&//check if there are four in a row
                        board[r + 1][c - 1] == p &&
                        board[r + 2][c - 2] == p &&
                        board[r + 3][c - 3] == p) {
                    return true;//return true if there are four in a row
                }
            }
        }
        return false;//default to return false
    }

    /**
     * Function: whatsAtPos(int r, int c)
     * Description: This function will return the char at the specified position
     * Input: int r, int c
     * Output: char[r][c]
     * @param r is the specified row
     * @param c is the specified column
     * @invariant 0 <= r < 6, 0 <= c < 7
     * @pre constructor must be called before using this function, user must verify correct input
     * @post no board changes made
     * @return the char at pos [r][c]
     */
    public static char whatsAtPos(int r, int c){
        return board[r][c];
    }

    /**
     * Function: toString()
     * Description: This function will print the game board
     * Input: none
     * Output: prints game board
     * @invariant board must be initialized
     * @pre constructor must be called before using this function
     * @post no variables are changed
     * @return board is converted to a single string
     */
    public String toString(){
        String board = "|0|1|2|3|4|5|6|\n";//start with header row
        for (int a = 0; a < 6; a++) {
            for (int b = 0; b <= 6; b++) {
                //add space if there is no char
                if (whatsAtPos(a, b) != 'X' && whatsAtPos(a, b) != 'O') {
                    board += "| ";
                } else//add char if present
                    board += ("|" + whatsAtPos(a, b));
            }
            board += ("|\n");//add closing end bracket and new line char
        }
        return board;//return the finished string
    }

    /**
     * Function: checkTie()
     * Description: This function will check for a draw
     * Input: none
     * Output: true/false
     * @invariant board initialized, checkForWin previously called
     * @pre user must call the constructor before using this function
     * @post no values changed
     * @return returns true if there are no free variable
     */
    public static boolean checkTie(){
        //loop through the columns and check if any have free spaces
        for (int a = 0; a < columns; a++){
            //if any columns are free then return false
            if (checkIfFree(a) == true){
                return false;
            }
        }
        return true;//defaults to return true
    }
}
