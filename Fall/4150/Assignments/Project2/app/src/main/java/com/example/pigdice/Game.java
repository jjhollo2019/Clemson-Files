/* Jeremy Holloway
 * CPSC-4150-001
 * 9/24/2019
 * HW2
 */
package com.example.pigdice;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class Game extends AppCompatActivity implements DialogFragment1.DialogFragmentListener {

    //turn strings for the game
    public static final String PTurn = "Player";
    public static final String CTurn = "Computer";

    //variables needed for game tracking
    private int SCORE_TO_WIN = 25;
    static int CURRENT_SCORE = 0;
    static int PLAYER_BANK = 0;
    static int COMPUTER_BANK = 0;
    static boolean PLAYER_TURN = true;
    private Dice mDice;
    private ImageView mDiceImageView;
    private Menu mMenu;
    private CountDownTimer mTimer;

    /**
     * This function is to check if either player has won the game
     * @pre PLAYER_BANK, COMPUTER_BANK, SCORE_TO_WIN must be initialized before use
     * @post winner fragment is called to congratulate the winner
     * @return true is returned if either score is higher than the SCORE_TO_WIN or else false is returned
     */
    public boolean checkWin(){
        //if either score is higher
        if(PLAYER_BANK >= SCORE_TO_WIN || COMPUTER_BANK >= SCORE_TO_WIN){
            return true;
        }
        //else no one has one
        else
            return false;
    }

    /**
     * This function will get and display the dialog to set the SCORE_TO_WIN
     * @pre Dialogfragment1 Listener must be implemented
     * @post SCORE_TO_WIN is set to the new value
     */
    public void showDialog() {
        //create a fragment
        DialogFragment1 dialogFragment = new DialogFragment1();
        //call the dialog
        dialogFragment.show(getSupportFragmentManager(), "DialogFragment1");
    }

    /**
     * This function sets the user input to SCORE_TO_WIN
     * @pre showDialog() must be called
     * @post SCORE_TO_WIN = user input, textView is updated
     * @param dialogFragment = fragment used to set the text
     */
    @Override
    public void onDialogPositiveClick(DialogFragment1 dialogFragment){
        //call function to get the editText value
        SCORE_TO_WIN = dialogFragment.getScore();
        //set the new SCORE_TO_WIN
        TextView textView = findViewById(R.id.setScore);
        textView.setText(String.valueOf(SCORE_TO_WIN));
    }

    /**
     * This function will return the player to the home screen if they don't want to play
     * @pre showDialog() must be called
     * @post user is returned to the main activity
     * @param dialogFragment = the fragment called by showDialog()
     */
    @Override
    public void onDialogNegativeClick(DialogFragment1 dialogFragment){
        //use intent to start home activity
        Intent intent = new Intent(Game.this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * This function specifies what to do upon activity creation
     * @pre The class must be called prior to use
     * @post the game is awaiting player response
     * @param savedInstanceState = the saved game variables
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //call super and layout tool bar
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //call dialog box
        showDialog();

        //display current score
        TextView textView = findViewById(R.id.displayTally);
        textView.setText(String.valueOf(CURRENT_SCORE));

        //TextView textView = findViewById(R.id.setScore);
        //textView.setText(String.valueOf(SCORE_TO_WIN));

        TextView textView2 = findViewById(R.id.displayPB);
        textView2.setText(String.valueOf(PLAYER_BANK));

        TextView textView3 = findViewById(R.id.displayCB);
        textView3.setText(String.valueOf(COMPUTER_BANK));

        mDice = new Dice(6);
        mDiceImageView = findViewById(R.id.dice1);

        showDice();
        TextView textView1 = findViewById(R.id.editTurn);
        textView1.setText(PTurn);
    }

    /**
     * This is a helper function that returns the dice value currently shown
     * @pre dice image must be actively displayed
     * @post the matching value of the die show is returned to the user
     * @return the value of the die is returned
     */
    private int getDiceValue() {
        //switch on the image id, defaults to return zero
        switch(mDice.getImageId()){
            case R.drawable.dice_1:
                return 1;

            case R.drawable.dice_2:
                return 2;

            case R.drawable.dice_3:
                return 3;

            case R.drawable.dice_4:
                return 4;

            case R.drawable.dice_5:
                return 5;

            case R.drawable.dice_6:
                return 6;
        }
        return 0;
    }

    /**
     * This function will inflate the options menu
     * @pre the class must be instantiated
     * @post the option menu is visible to the user
     * @param menu = a declared menu item to retrieve the stored menu
     * @return returns true if the function sucessfully creates the menu, otherwise false if failed
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate and set menu
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        mMenu = menu;
        //return status to super
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * This function will set the dice image
     * @pre the image resources must be in the drawable image folder
     * @post the imageView is correctly set
     */
    private void showDice() {
        //declare drawable
        Drawable diceDrawable;
        //if newer SDK
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            diceDrawable = getResources().getDrawable(mDice.getImageId(), getApplicationContext().getTheme());
        }
        //else use deprecated method
        else {
            diceDrawable = getResources().getDrawable(mDice.getImageId());
        }
        //set image view
        mDiceImageView.setImageDrawable(diceDrawable);
        //set description
        mDiceImageView.setContentDescription(Integer.toString(mDice.getNumber()));
    }

    /**
     * This function implements the call back methods for the options menu
     * @pre the onCreateOptionsMenu function must be called
     * @post the selected action is performed
     * @param item = the specific item selected by the user
     * @return returns true is successful, otherwise returns false if failed
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //switch on item id
        switch(item.getItemId()) {
            //changing turn option
            case R.id.action_one:
                playerTurn();
                return true;

            //end game option
            case R.id.action_two:
                Intent intent = new Intent(Game.this, MainActivity.class);
                startActivity(intent);
                return true;

            //stop rolling option
            case R.id.action_stop:
                mTimer.cancel();
                item.setVisible(false);
                return true;

            //roll option
            case R.id.action_roll:
                rollDice();
                return true;

            //default option
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * This function will "roll" the dice
     * @pre showDice() must be called
     * @post the die value is possibly changed
     */
    private void rollDice() {
        //if the game is over
        if(checkWin()) {
            return;
        }
        //else roll the die
        else {
            mMenu.findItem(R.id.action_stop).setVisible(true);
            if (mTimer != null) {
                mTimer.cancel();
            }

            mTimer = new CountDownTimer(2000, 100) {
                /**
                 * This function calls the Dice roll function and sets the image
                 * @pre rollDice() must be called
                 * @post the dice image is possibly different
                 * @param l = the long for the timer countdown
                 */
                @Override
                public void onTick(long l) {
                    mDice.roll();
                    showDice();
                }

                /**
                 * This function sets the end of roll procedures
                 * @pre rollDice() must be called
                 * @post the cancel button is hidden and updateTally is called on the new dice value
                 */
                @Override
                public void onFinish() {
                    mMenu.findItem(R.id.action_stop).setVisible(false);
                    if(PLAYER_TURN) {
                        updateTally(getDiceValue());
                    }
                }
            }.start();
        }
    }

    /**
     * This function will update CURRENT_SCORE
     * @pre CURRENT_SCORE must be initialized
     * @post the updated score displayed to the user
     * @param diceVal = the value to update the score by
     */
    private void updateTally(int diceVal){
        //check if either player won the game
        if(checkWin()) {
            return;
        }
        //else keep playing the game
        else {
            //if the dice value is one then reset current score and change turns
            if (diceVal == 1) {
                CURRENT_SCORE = 0;
                playerTurn();
            }
            //else update CURRENT_SCORE and its associated textView
            else {
                CURRENT_SCORE += diceVal;
                TextView viewText = findViewById(R.id.displayTally);
                viewText.setText(String.valueOf(CURRENT_SCORE));
            }
        }
    }

    /**
     *
     */
    private void playerTurn(){
        if(checkWin()) {
            return;
        }
        else {
            System.out.println("start player turn");
            PLAYER_BANK += CURRENT_SCORE;
            TextView viewText = findViewById(R.id.displayPB);
            viewText.setText(String.valueOf(PLAYER_BANK));
            CURRENT_SCORE = 0;
            TextView textView1 = findViewById(R.id.displayTally);
            textView1.setText(String.valueOf(CURRENT_SCORE));
            System.out.println("end player turn");
            PLAYER_TURN = false;
            TextView textView = findViewById(R.id.editTurn);
            textView.setText(CTurn);
            computerTurn();
            System.out.println("end computer turn again");
            textView.setText(PTurn);
            PLAYER_TURN = true;
            CURRENT_SCORE = 0;
            textView1.setText(String.valueOf(CURRENT_SCORE));
        }
    }

    /**
     *
     */
    public void computerTurn(){
        if(checkWin()) {
            return;
        }
        else {
            boolean rollAgain = true;
            System.out.println("start computer turn");
            rollDice();
            Random random = new Random();
            int choice = random.nextInt(15);
            while ((choice % 3 == 0 || choice % 3 == 1) && rollAgain) {
                System.out.println("computer rolled");
                rollDice();
                if (getDiceValue() == 1) {
                    rollAgain = false;
                } else {
                    choice = random.nextInt(15);
                    updateTally(getDiceValue());
                }
            }
            COMPUTER_BANK += CURRENT_SCORE;
            TextView viewText1 = findViewById(R.id.displayCB);
            viewText1.setText(String.valueOf(COMPUTER_BANK));
            CURRENT_SCORE = 0;
            TextView textView2 = findViewById(R.id.displayTally);
            textView2.setText(String.valueOf(CURRENT_SCORE));
            System.out.println("end computer turn");
        }
    }

}
