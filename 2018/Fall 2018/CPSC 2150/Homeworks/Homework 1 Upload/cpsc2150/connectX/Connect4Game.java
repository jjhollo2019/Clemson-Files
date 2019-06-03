/* Jeremy Holloway
 * CPSC-2150-001
 * Homework 1
 * 9/22/2018
 */
package cpsc2150.connectX;

import java.util.*;
import java.lang.*;

public class Connect4Game {
    private static GameBoard newGame;
    private static Scanner keyboard = new Scanner(System.in);

    /**
     * Function: play()
     * Description: This function will take user inout and display the results
     * Input: none
     * Output: asks for user input and prints results
     * @invariant newGame must be initialized
     * @pre play must be called from the main
     * @post checkForWin == true
     */
    private static void play(){
        newGame = new GameBoard();//initialize game board
        int column;//declare input value
        System.out.println(newGame.toString());//print board
        boolean gameOn = true;//set continue condition
        while (gameOn) {
            //ask for player input
            System.out.println("Player X, what column would you like to place your marker in?");
            column = Integer.parseInt(keyboard.nextLine());//grab the column input
            //verify input is in range of the board
            while (column < 0) {
                System.out.println("Column cannot be less than 0");
                System.out.println("Player X, what column would you like to place your marker in?");
                column = Integer.parseInt(keyboard.nextLine());//grab column input
            }
            while (column >= 7){
                System.out.println("Column cannot be greater than 6");
                System.out.println("Player X, what column would you like to place your marker in?");
                column = Integer.parseInt(keyboard.nextLine());//grab column input
            }
            //only call placeToken if the column is free
            while (newGame.checkIfFree(column) == false) {
                System.out.println("Column is full");//display error if column is full
                //ask the user for input
                System.out.println("Player X, what column would you like to place your marker in?");
                column = Integer.parseInt(keyboard.nextLine());//grab input
            }
            newGame.placeToken('X', column);//place token
            if (newGame.checkForWin(column) == true) {//check for winning move
                System.out.println(newGame.toString());//print board
                System.out.println("Player X won!");//print winner if true
                break;//break out of loop after winning
            }
            //check for a tie game
            if (newGame.checkTie() == true){
                System.out.println(newGame.toString());//print board
                System.out.println("The game is a draw!");//print message if game ties
                break;//break
            }
            System.out.println(newGame.toString());//print board

            //ask for second player input
            System.out.println("Player O, what column would you like to place your marker in?");
            column = Integer.parseInt(keyboard.nextLine());//grab input
            while (column < 0) {
                System.out.println("Column cannot be less than 0");
                System.out.println("Player O, what column would you like to place your marker in?");
                column = Integer.parseInt(keyboard.nextLine());//grab column input
            }
            while (column >= 7){
                System.out.println("Column cannot be greater than 6");
                System.out.println("Player O, what column would you like to place your marker in?");
                column = Integer.parseInt(keyboard.nextLine());//grab column input
            }
            //only call placeToken if the desired column is free
            while (newGame.checkIfFree(column) == false) {
                System.out.println("Column is full");//print error if column is full
                //ask for new input
                System.out.println("Player O, what column would you like to place your marker in?");
                column = Integer.parseInt(keyboard.nextLine());//grab new input
            }
            newGame.placeToken('O', column);//place token
            if (newGame.checkForWin(column) == true) {//check for winning move
                System.out.println(newGame.toString());//print board
                System.out.println("Player O wins!");//print winner if true
                break;//break from loop after winning
            }
            //check for a tie game
            if (newGame.checkTie() == true){
                System.out.println(newGame.toString());//print board
                System.out.println("The game is a draw!");//print message if game ties
                break;//break
            }
            System.out.println(newGame.toString());//print board
        }
    }

    /**
     * Function: main(String[] args)
     * Description: This function will call the functions to play the game
     * Input: none
     * Output: none
     */
    public static void main(String[] args) {
        String answer = "";//set default continue condition
        do{
            play();//call the game method
            //ask to continue
            System.out.println("Would you like to play again? Y/N");
            answer = keyboard.nextLine();
            while (!answer.equalsIgnoreCase("Y") && !answer.equalsIgnoreCase("N")){
                System.out.println("Would you like to play again? Y/N");
                answer = keyboard.nextLine();
            }
            //continue if input remains unchanged or user enters y or Y
        }while(answer.equalsIgnoreCase("Y") || answer.equals(""));
    }
}