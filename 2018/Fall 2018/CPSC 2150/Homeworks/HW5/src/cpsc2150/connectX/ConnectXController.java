/* Jeremy Holloway
 * CPSC-2150-001
 * Homework 5
 * 12/05/2018
 */
package cpsc2150.connectX;


/**
 * The controller class will handle communication between our View and our Model (IGameBoard)
 *
 * This is where you will write code
 *
 * You will need to include your IGameBoard interface
 * and both of the IGameBoard implementations from Homework 3
 * If your code was correct you will not need to make any changes to your IGameBoard implementation class
 */

public class ConnectXController {
    //our current game that is being played
    private IGameBoard curGame;
    //The screen that provides our view
    private ConnectXView screen;
    //use a global player index to track whose turn it is
    private static int playerIndex = 0;
    //variable to decide if a new game should be started
    private boolean replay = false;
    //maximum number of players that can play a single game
    public static final int MAX_PLAYERS = 10;
    //our play tokens are hard coded. We could make a screen to get those from the user, but
    //I want to keep this example simple
    private char[] players = {'X', 'O', 'Y', 'Z', 'A', 'K', 'E', 'J', 'N', 'H'};
    //current number of players
    private int numPlayers;


    /**
     *
     * @param model the board implementation
     * @param view the screen that is shown
     * @post the controller will respond to actions on the view using the model.
     */
    ConnectXController(IGameBoard model, ConnectXView view, int np){
        this.curGame = model;
        this.screen = view;
        numPlayers = np;
    }

    /**
     * Function: processButtonClick(int col)
     * Description: This function will handle the general flow of the game
     * @param col the column of the activated button
     * @post will allow the player to place a token in the column if it is not full, otherwise it will display an error
     * and allow them to pick again. Will check for a win as well. If a player wins it will allow for them to play another
     * game hitting any button
     */
    public void processButtonClick(int col) {
        if (replay) newGame();//check for new game
        boolean valid = curGame.checkIfFree(col);//determine if move is valid
        boolean token = false;//set a token placement condition
        if(!valid)screen.setMessage("Column full!");//display error if move is invalid
        if(valid) {//if move is valid
            int i = 0;//set row iterator
            while (!token) {//while the token hasn't been placed
                if (curGame.whatsAtPos(i, col) == ' ') {//check for empty space in the column
                    screen.setMarker(i, col, players[playerIndex]);//set the marker
                    curGame.placeToken(players[playerIndex], col);//place the token in the model
                    token = true;//break out of the loop
                }
                i++;//otherwise continue through the row
            }
        }
        boolean winner = curGame.checkForWin(col);//check for a winner
        boolean tie = curGame.checkTie();//check for a tied game
        if (!winner && !tie) {//if the game hasn't been won or tied
            playerIndex++;//change players
            if (playerIndex == numPlayers) playerIndex = 0;//reset player if needed
            screen.setMessage("It is " + players[playerIndex] + "'s turn.");//tell the player whose turn it is
        }
        else if(tie){//check for a tied game
            screen.setMessage("The game is a tie!\nClick any button to play again");//display tie message and ask to play again
            replay = true;//set replay condition
            playerIndex = 0;//reset player index
        }
        else {//else the the player won the game
            screen.setMessage("Player " + players[playerIndex] + " won!\nClick any button to play again");//display winner and ask to play again
            replay = true;//set replay condition
            playerIndex = 0;//reset player index
        }
    }

    /**
     * This method will start a new game by returning to the setup screen and controller
     */
    private void newGame()
    {
        //close the current screen
        screen.dispose();
        //start back at the set up menu
        SetupView screen = new SetupView();
        SetupController controller = new SetupController(screen);
        screen.registerObserver(controller);
    }
}
