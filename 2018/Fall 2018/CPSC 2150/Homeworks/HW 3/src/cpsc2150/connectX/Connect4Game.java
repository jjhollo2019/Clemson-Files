/* Jeremy Holloway
 * CPSC-2150-001
 * Homework #3
 * 10/28/2018
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
     */
    public static void main(String[] args) {
        Map<Character, Integer> players = new LinkedHashMap<>();
        String answer;//declare an input string
        String gameType = "";
        int playerNum;
        Character player;
        boolean gameReset = true;//set main control condition
        boolean input = false;//set input control condition
        int rows = 0;//set row input
        int columns = 0;//set column input
        int win = 0;//set win input
        do {//first loop to control the entire game
            while (!input) {//input loop
                System.out.println("How many players?");//ask for number of players
                playerNum = Integer.parseInt(keyboard.nextLine());//get input
                while (playerNum < 2 || playerNum > 10) {//check if number of players is out of scope
                    if (playerNum < 2) {//if less than 2
                        System.out.println("Must be at least 2 players.");
                        System.out.println("How many players?");
                        playerNum = Integer.parseInt(keyboard.nextLine());//grab input
                    }
                    if (playerNum > 10) {//if greater than 10
                        System.out.println("Must be 10 players or fewer.");
                        System.out.println("How many players?");
                        playerNum = Integer.parseInt(keyboard.nextLine());//grab input
                    }
                }
                for (int i = 0; i < playerNum; i++) {//run a loop to get player tokens
                    System.out.println("Enter the character to represent player " + (i + 1));
                    answer = keyboard.nextLine();//grab input
                    answer = answer.toUpperCase();//convert to uppercase
                    player = answer.charAt(0);//grab the char
                    while (players.containsKey(player)) {//check if key is already taken
                        System.out.println(player + " is already taken as a player token!");
                        System.out.println("Enter the character to represent player " + (i + 1));
                        answer = keyboard.nextLine();//grab input
                        answer = answer.toUpperCase();//convert to uppercase
                        player = answer.charAt(0);//grab the char
                    }
                    players.put(player, i);//add the token
                }
                System.out.println("How many rows should be on the board?");
                rows = Integer.parseInt(keyboard.nextLine());//set the number of rows
                while (rows < min || rows > max) {//check for out of bounds condition
                    if (rows < min) {//condition for less than 3
                        System.out.println("Must have at least 3 rows.");
                        System.out.println("How many rows should be on the board?");
                        rows = Integer.parseInt(keyboard.nextLine());//grab input
                    }
                    if (rows > max) {//condition for greater than 100
                        System.out.println("Can have at most 100 rows");
                        System.out.println("How many rows should be on the board?");
                        rows = Integer.parseInt(keyboard.nextLine());//grab input
                    }
                }
                System.out.println("How many columns should be on the board?");
                columns = Integer.parseInt(keyboard.nextLine());//grab input
                while (columns < min || columns > max) {//check for out of bounds condition
                    if (columns < min) {//condition for less than 3
                        System.out.println("Must have at least 3 columns.");
                        System.out.println("How many columns should be on the board?");
                        columns = Integer.parseInt(keyboard.nextLine());//grab input
                    }
                    if (columns > max) {//condition for greater than 100
                        System.out.println("Can have at most 100 columns");
                        System.out.println("How many columns should be on the board?");
                        columns = Integer.parseInt(keyboard.nextLine());//grab input
                    }
                }
                System.out.println("How many in a row to win?");
                win = Integer.parseInt(keyboard.nextLine());
                while (win < min || win > winMax) {//check for out of bounds condition
                    if (win < min) {//condition for less than 3
                        System.out.println("Must have at least 3 in a row to win.");
                        System.out.println("How many in a row to win?");
                        win = Integer.parseInt(keyboard.nextLine());
                    }
                    if (win > winMax) {//check for condition greater than 25
                        System.out.println("Can have at most 25 in a row to win");
                        System.out.println("How many in a row to win?");
                        win = Integer.parseInt(keyboard.nextLine());//grab input
                    }
                }
                //ask for memory efficient or fast game
                System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)?");
                gameType = keyboard.nextLine();//grab input
                gameType = gameType.toUpperCase();//convert to upper case
                while (!gameType.equals("F") && !gameType.equals("M")) {
                    System.out.println("Please enter F or M.");
                    System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)?");
                    gameType = keyboard.nextLine();//grab input
                    gameType = gameType.toUpperCase();//convert to uppercase
                }
                input = true;//set control to true
            }//end input loop
            IGameBoard newGame;
            if (gameType.equals("F")) {
                newGame = new GameBoard(rows, columns, win);
            } else {
                newGame = new GameBoardMem(rows, columns, win);
            }
            boolean markerVal;
            int marker;//set marker input
            boolean gameOn = true;
            while(gameOn){//while the game is being played
                System.out.println(newGame.toString());//print board
                for(Map.Entry m: players.entrySet()) {//loop through the player tokens
                    markerVal = false;
                    System.out.println("Player " + m.getKey() + ", what column do you want to place your marker in?");
                    marker = Integer.parseInt(keyboard.nextLine());
                    //check if marker placement is valid
                    while (!markerVal) {
                        if (marker < firstCol) {//check for condition marker less than 0
                            System.out.println("Column cannot be less than 0");
                            System.out.println("Player " + m.getKey() +
                                    ", what column do you want to place your marker in?");
                            marker = Integer.parseInt(keyboard.nextLine());//grab input
                        }
                        if (marker > columns) {//check for condition marker greater than available columns
                            System.out.println("Column cannot be greater than " + (newGame.getNumColumns() - 1));
                            System.out.println("Player " + m.getKey() +
                                    ", what column do you want to place your marker in?");
                            marker = Integer.parseInt(keyboard.nextLine());//grab input
                        }
                        if (marker >= firstCol && marker < newGame.getNumColumns()) {//check for condition columns isn't free
                            if (!newGame.checkIfFree(marker)) {
                                System.out.println("Column is full");//display error if column is full
                                //ask the user for input
                                System.out.println("Player " + m.getKey() +
                                        ", what column would you like to place your marker in?");
                                marker = Integer.parseInt(keyboard.nextLine());//grab input
                            } else {
                                markerVal = true;
                            }
                        }
                    }
                    newGame.placeToken((char)m.getKey(), marker);//place token
                    if (newGame.checkForWin(marker)) {//check for winning move
                        System.out.println(newGame.toString());//print board
                        System.out.println("Player " + m.getKey() + " won!");//print winner if true
                        gameOn = false;
                        gameReset = false;//set main control condition to false
                        break;//break out of the playing loop
                    }
                    if (newGame.checkTie()) {//check for tie
                        System.out.println("The game is a tie!");
                        gameOn = false;
                        gameReset = false;//set the main control condition to false
                        break;//break out of the playing loop
                    }
                    System.out.println(newGame.toString());//print board with player move
                }
            }
            System.out.println("Would you like to play again? Y/N\n");//check if player wants to play again
            answer = keyboard.nextLine();//grab input
            if (answer.equalsIgnoreCase("N")) {//check for condition player doesn't want to play
                keyboard.close();//close scanner
                System.exit(0);//exit game
            }
            else {
                gameReset = true;//otherwise reset the game
                input = false;//reset input loop
                players.clear();//clear the player tokens
            }
        } while (gameReset);//main game control loop
    }
}