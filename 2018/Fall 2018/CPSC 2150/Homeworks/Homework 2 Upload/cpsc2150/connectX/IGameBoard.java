/* Jeremy Holloway
 * CPSC-2150-001
 * Homework #2
 * 10/10/2018
 */

package cpsc2150.connectX;

/**
 * Initialization ensures:
 *  The game board will contain only blank spaces and be size 100 by 100 or smaller
 *  The game will require at least 3 but not more than 25 tokens in a row to win
 * Defines:
 *  number_of_rows: r
 *  number_of_columns: c
 *  number_to_win: win
 * Constraints:
 *  0 < number_of_rows <= 100
 *  0 < number_of_columns <= 100
 *  0 < number_to_win <= 25
 */
public interface IGameBoard
{
    /**
     * Function: getNumRows()
     * Description: This function will return the number of rows
     * Input: none
     * Output: int row
     * @return the number of rows on the game board
     */
    public int getNumRows();

    /**
     * Function: getNumColumns()
     * Description: This function will return the number of columns
     * Input: none
     * Output: int column
     * @return the number of columns on the game board
     */
    public int getNumColumns();

    /**
     * Function: getNumToWin()
     * Description: This function will return the number to win this game
     * Input: none
     * Output: int winNum
     * @return the number required to win on this board instance
     */
    public int getNumToWin();

    /**
     * Function: checkIfFree(int c)
     * Description: This function will determine if the row is free
     * Intput: int c
     * Output: true/false
     * @param c is the user selected column to place a token
     * @pre int c < column && int c > 0
     * @post user receives boolean indication if free/not free
     * @return true if column is free / false if column is full
     */
    default public boolean checkIfFree(int c)
    {
        for(int a = 0; a < getNumColumns(); a++){//loop through the rows of the selected column
            //return true if a free space is found
            if( whatsAtPos(a, c) != 'X' && whatsAtPos(a, c) != 'O'){
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
     * @pre int c < column && int c > 0, int c is the column of the most recent marker placement
     * @post user receives boolean indication if they won/didn't win
     * @return true if any condition is met / false if none are met
     */
    default public boolean checkForWin(int c)
    {
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
     * @pre int c < columns && int c > 0 && char p == ('X' || 'O') && checkIfFree(int c) == true
     * @post board space contains char p
     */
    public void placeToken(char p, int c);

    /**
     * Function: checkHorizWin(int r, int c, char p)
     * Description: This function checks for a row win
     * Input: int r, int c, char p
     * Output: true/false
     * @param r is the row to check
     * @param c is the column to check
     * @param p is the char to check for
     * @pre int r >= 3 && int r < row && int c >= 3 && int c < column && char p == ('X' || 'O')
     *  int c is column of the most recent marker placement
     * @post user receives boolean indication if there is a winner
     * @return true if there is a horizontal win, else return false
     */
    default public boolean checkHorizWin(int r, int c, char p)
    {
        int winCount = 0;//declare a char counter
        //loop through the board
        for (r = 0; r < getNumRows(); r++) {
            for (c = 0; c < getNumColumns(); c++) {
                if (whatsAtPos(r, c) == p){//increment counter if position equals p
                    winCount++;
                }
                else{//reset to zero if p is not sequential
                    winCount = 0;
                }
                if (winCount == getNumToWin()) {//return true if there are at least 4 p's
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
     * @pre int r < row && int r >= 0 && int c >= 0 && int c < column && char p == ('X' || 'O')
     *  int c is column of the most recent marker placement
     * @post user receives boolean indication if there is a winner
     * @return true if there is a winner else false for no winner
     */
    default public boolean checkVertWin(int r, int c, char p)
    {
        int winCount = 0;//declare a char counter
        //loop through the board to determine a win
        for (r = 0; r < getNumRows(); r++){
            if (whatsAtPos(r, c) == p){//count if position equals p
                winCount++;
            }
            else{//reset if position does not equal p
                winCount = 0;
            }
            if (winCount == getNumToWin()){//return true if there are four in a row
                return true;
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
     * @pre int r < row && int r >= 0 && int c >= 0 && int c < column && char p == ('X' || 'O')
     *  int c is column of the most recent marker placement
     * @post user receives boolean indication if there is a winner
     * @return true if there is a winner else false for no winner
     */
    default public boolean checkDiagWin(int r, int c, char p)
    {
        int winCount = 0;
        //check forward diagonal win
        for (r = 0; r < getNumRows() - (getNumToWin() - 1); r++) {//loop through rows
            for (c = 0; c < getNumColumns() - (getNumToWin() - 1); c++) {//loop through columns
                for (int a = 0; a < getNumToWin(); a++){
                    if (whatsAtPos((r + a), (c + a)) == p){
                        winCount++;
                    }
                    else
                        winCount = 0;
                }
                if (winCount >= getNumToWin()){
                    return true;
                }
            }
        }
        winCount = 0;
        //check backwards diagonal win
        for (r = 0; r < getNumRows() - (getNumToWin() - 1); r++) {//loop through the rows
            for (c = (getNumToWin() - 1); c < getNumColumns(); c++) {//loop through the columns
                for (int a = 0; a < getNumToWin(); a++){
                    if (whatsAtPos((r + a), (c - a)) == p){
                        winCount++;
                    }
                    else
                        winCount = 0;
                }
                if (winCount >= getNumToWin()){
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
     * @pre int r < row && int r >= 0 && int c >= 0 && int c < column && char p == ('X' || 'O')
     * @post user receives boolean indication if there is a winner
     * @return true if there is winner else returns false
     */
    public char whatsAtPos(int r, int c);

    /**
     * Function: checkTie()
     * Description: This function will check for a draw
     * Input: none
     * Output: true/false
     * @pre checkHorizWin == false && checkVertWin == false && checkDiagWin == false
     * @post user receives boolean indication
     * @return returns true if there are no free columns
     */
    default public boolean checkTie()
    {
        //loop through the columns and check if any have free spaces
        for (int a = 0; a < getNumColumns(); a++){
            //if any columns are free then return false
            if (checkIfFree(a) == true){
                return false;
            }
        }
        return true;//defaults to return true
    }
}
