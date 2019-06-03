/* Jeremy Holloway
 * CPSC-2150-001
 * Homework #4
 * 11/18/2018
 */
package cpsc2150.connectX;

import java.util.*;
/**
 * @invariant 0 < row <= 100
 * @invariant 0 < column <= 100
 * @invariant 0 < winNum <= 25
 * Correspondence number_of_rows: row
 * Correspondence number_of_columns: column
 * Correspondence number_to_win: winNum
 * Correspondence this = board<0...column-1>
*/
public class GameBoardMem implements IGameBoard {
    private static Map<Integer, List<Character>> board;
    private static int row;
    private static int column;
    private static int winNum;

    /**
     * Function: GameBoard()
     * Description: This function will initialize the game board
     * @pre row >= 3 && row <= 100, column >= 3 && column <= 100, winNum >= 3 && winNum <= 25
     * @post board[][] = board[row][column]
     */
    public GameBoardMem(int rows, int columns, int win)
    {
        board = new HashMap<>();
        row = rows;
        column = columns;
        winNum = win;
    }

    /**
     * Function: placeToken(char p, int c)
     * Description: This function will place the player token on the board
     * @param p is the player token to place
     * @param c is the player selected column
     */
    public void placeToken(char p, int c){
        board.computeIfAbsent(c, k -> new LinkedList<>());
        board.get(c).add(p);//place char
    }

    /**
     * Function: whatsAtPos(int r, int c)
     * Description: This function will return what is at the position on the board
     * @param r is the specified row
     * @param c is the specified column
     * @return The character at the specified position is returned
     */
    public char whatsAtPos(int r, int c){
        List list = board.get(c);
        if (board.get(c) == null)
            return ' ';
        else if (board.get(c).size() <= r)
            return ' ';
        else
            return (char) list.get(r);
    }

    /**
     * Function: toString()
     * Description: This function will print the game board
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
        for (int a = getNumRows()-1; a >= 0; a--) {
            for (int b = 0; b < column; b++) {
                board += ("|" + whatsAtPos(a, b) + ' ');
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


