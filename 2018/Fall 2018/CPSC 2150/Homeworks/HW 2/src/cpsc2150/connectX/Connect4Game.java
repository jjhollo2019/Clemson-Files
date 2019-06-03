/* Jeremy Holloway
 * CPSC-2150-001
 * Homework #2
 * 10/10/2018
 */

package cpsc2150.connectX;

import java.util.*;
import java.lang.*;

/**
 * Function: connect4Game class
 * Description: This function holds the main function for the connect 4 game
 * @invariant game must be played on a 2d char array, cannot be larger than 100 x 100 board,
 * can not be smaller than 3 x 3, must connect at least 3 markers, can not connect more than 25
 */
public class Connect4Game {
    private static Scanner keyboard = new Scanner(System.in);
    private static final int firstCol = 0;
    private static final int min = 3;
    private static final int max = 100;
    private static final int winMax = 25;

    /**
     * Function: main(String[] args)
     * Description: This function will call the functions to play the game
     * Input: none (doesn't use String[] args)
     * Output: none
     */
    public static void main(String[] args) {
        String answer;//declare an input string
        boolean gameReset = true;//set main control condition
        boolean input = false;//set input control condition
        int rows = 0;//set row input
        int columns = 0;//set column input
        int win = 0;//set win input
        do {//first loop to control the entire game
            while(!input){//input loop
                System.out.println("How many rows should be on the board?");
                rows = keyboard.nextInt();//set the number of rows
                while (rows < min || rows > max) {//check for out of bounds condition
                    if (rows < min) {//condition for less than 3
                        System.out.println("Must have at least 3 rows.");
                        System.out.println("How many rows should be on the board?");
                        rows = keyboard.nextInt();//grab input
                    }
                    if (rows > max) {//condition for greater than 100
                        System.out.println("Can have at most 100 rows");
                        System.out.println("How many rows should be on the board?");
                        rows = keyboard.nextInt();//grab input
                    }
                }
                System.out.println("How many columns should be on the board?");
                columns = keyboard.nextInt();//grab input
                while (columns < min || columns > max) {//check for out of bounds condition
                    if (columns < min) {//condition for less than 3
                        System.out.println("Must have at least 3 columns.");
                        System.out.println("How many columns should be on the board?");
                        columns = keyboard.nextInt();//grab input
                    }
                    if (columns > max) {//condition for greater than 100
                        System.out.println("Can have at most 100 columns");
                        System.out.println("How many columns should be on the board?");
                        columns = keyboard.nextInt();//grab input
                    }
                }
                System.out.println("How many in a row to win?");
                win = keyboard.nextInt();
                while (win < min || win > winMax) {//check for out of bounds condition
                    if (win < min) {//condition for less than 3
                        System.out.println("Must have at least 3 in a row to win.");
                        System.out.println("How many in a row to win?");
                        win = keyboard.nextInt();
                    }
                    if (win > winMax) {//check for condition greater than 25
                        System.out.println("Can have at most 25 in a row to win");
                        System.out.println("How many in a row to win?");
                        win = keyboard.nextInt();//grab input
                    }
                }
                input = true;//set control to true
            }//end input loop
            //verify all input conditions are met
            if (rows < min || rows > max || columns < min || columns > max || win < min || win > winMax) {
                input = false;//reset control loop
            }
            //otherwise initialize the game
            IGameBoard newGame = new GameBoard(rows, columns, win);
            boolean playerX = true;//set player X condition
            boolean playerO = true;//set player O condition
            boolean markerVal = false;
            int marker;//set marker input
            do {
                while(playerO) {//player X will go as long as player O hasn't won
                    System.out.println(newGame.toString());//print board
                    System.out.println("Player X, what column do you want to place your marker in?");
                    marker = keyboard.nextInt();
                    //check if marker placement is valid
                    while (!markerVal) {
                        if (marker < firstCol) {//check for condition marker less than 0
                            System.out.println("Column cannot be less than 0");
                            System.out.println("Player X, what column do you want to place your marker in?");
                            marker = keyboard.nextInt();//grab input
                        }
                        if (marker > columns) {//check for condition marker greater than available columns
                            System.out.println("Column cannot be greater than " + (newGame.getNumColumns() - 1));
                            System.out.println("Player X, what column do you want to place your marker in?");
                            marker = keyboard.nextInt();//grab input
                        }
                        if (marker > firstCol && marker < newGame.getNumColumns()) {//check for condition columns isn't free
                            if (newGame.checkIfFree(marker) == false) {
                                System.out.println("Column is full");//display error if column is full
                                //ask the user for input
                                System.out.println("Player X, what column would you like to place your marker in?");
                                marker = keyboard.nextInt();//grab input
                            }
                            else{
                                markerVal = true;
                            }
                        }
                    }
                    newGame.placeToken('X', marker);//place token
                    if (newGame.checkForWin(marker) == true) {//check for winning move
                        System.out.println(newGame.toString());//print board
                        System.out.println("Player X won!");//print winner if true
                        playerX = false;//set control condition to false
                        playerO = false;//set control condition to false
                        gameReset = false;//set main control condition to false
                    }

                    //check for a tie game
                    else if (newGame.checkTie() == true) {//check for tie
                        System.out.println(newGame.toString());//print board
                        System.out.println("The game is a draw!");//print message if game ties
                        playerX = false;//set control condition to false
                        playerO = false;//set control condition to false
                        gameReset = false;//set main control condition to false
                    }
                    else{
                        playerO = false;//end player X's turn
                        playerX = true;//start player O's turn
                        markerVal = false;
                    }
                    System.out.println(newGame.toString());//print board with player move
                }
                while (playerX) {//player O will have a turn as long as player X hasn't won
                    System.out.println("Player O, what column do you want to place your marker in?");
                    marker = keyboard.nextInt();//grab input
                    while (!markerVal) {
                        if (marker < firstCol) {//check for condition marker less than 0
                            System.out.println("Column cannot be less than 0");
                            System.out.println("Player O, what column do you want to place your marker in?");
                            marker = keyboard.nextInt();//grab input
                        }
                        if (marker > columns) {//check for condition marker greater than available columns
                            System.out.println("Column cannot be greater than " + (newGame.getNumColumns() - 1));
                            System.out.println("Player O, what column do you want to place your marker in?");
                            marker = keyboard.nextInt();//grab input
                        }
                        if (marker > firstCol && marker < newGame.getNumColumns()) {//check for condition columns isn't free
                            if (newGame.checkIfFree(marker) == false) {
                                System.out.println("Column is full");//display error if column is full
                                //ask the user for input
                                System.out.println("Player O, what column would you like to place your marker in?");
                                marker = keyboard.nextInt();//grab input
                            }
                            else{
                                markerVal = true;
                            }
                        }
                    }
                    newGame.placeToken('O', marker);//place token
                    if (newGame.checkForWin(marker) == true) {//check for winning move
                        System.out.println(newGame.toString());//print board
                        System.out.println("Player O won!");//print winner if true
                        playerO = false;//set control condition to false
                        playerX = false;//set control condition to false
                        gameReset = false;//set main control condition to false
                    }
                    //check for a tie game
                    else if (newGame.checkTie() == true) {
                        System.out.println(newGame.toString());//print board
                        System.out.println("The game is a draw!");//print message if game ties
                        playerO = false;//set control condition to false
                        playerX = false;//set control condition to false
                        gameReset = false;//set main control condition to false
                    }
                    else{
                        playerX = false;//end player O's turn
                        playerO = true;//start player X's turn
                        markerVal = false;
                    }
                    System.out.println(newGame.toString());//print board with player's move
                }
            } while (playerX == true || playerO == true);//continue to play while it's a player's turn
            System.out.println("Would you like to play again? Y/N\n");//check if player wants to play again
            answer = keyboard.nextLine();//flush next line, it will skip if I don't do this
            answer = keyboard.nextLine();//grab input
            if (answer.equalsIgnoreCase("N")) {//check for condition player doesn't want to play
                keyboard.close();//close scanner
                System.exit(0);//exit game
            }
            else {
                gameReset = true;//otherwise reset the game
                input = false;//reset input loop
            }
        } while (gameReset);//main game control loop
    }
}