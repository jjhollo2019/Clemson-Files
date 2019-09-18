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
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class Game extends AppCompatActivity implements DialogFragment1.DialogFragmentListener {

    public static final int MAX_DICE = 3;
    public static final String PTurn = "Player Turn";
    public static final String CTurn = "Computer Turn";

    private int SCORE_TO_WIN;
    private int DICE_VALUE;
    private int PLAYER_SCORE = 0;
    private int COMPUTER_SCORE = 0;
    private int PLAYER_BANK = 0;
    private int COMPUTER_BANK = 0;
    private boolean PLAYER_TURN = false;
    private boolean COMPUTER_TURN = false;
    private int mVisibleDice;
    private Dice[] mDice;
    private ImageView[] mDiceImageViews;
    private Menu mMenu;
    private CountDownTimer mTimer;

    public void showDialog() {
        DialogFragment1 dialogFragment = new DialogFragment1();
        dialogFragment.show(getSupportFragmentManager(), "DialogFragment1");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment1 dialogFragment){
        SCORE_TO_WIN = dialogFragment.getScore();
        EditText editText = findViewById(R.id.setScore);
        editText.setText(SCORE_TO_WIN);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment1 dialogFragment){
        Intent intent = new Intent(Game.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EditText editText = findViewById(R.id.displayTally);
        editText.setText(PLAYER_SCORE);

        EditText editText1 = findViewById(R.id.setComputerTally);
        editText1.setText(COMPUTER_SCORE);

        EditText editText2 = findViewById(R.id.displayPB);
        editText2.setText(PLAYER_BANK);

        EditText editText3 = findViewById(R.id.displayCB);
        editText3.setText(COMPUTER_BANK);

        EditText editText4 = findViewById(R.id.setScore);
        editText4.setText(SCORE_TO_WIN);

        playGame(editText, editText2, editText1, editText3, editText4);

        showDialog();

        mDice = new Dice[MAX_DICE];
        for (int i = 0; i < MAX_DICE; i++) {
            mDice[i] = new Dice(i + 1);
        }

        //fix from array
        mDiceImageViews = new ImageView[MAX_DICE];
        mDiceImageViews[0] = findViewById(R.id.dice1);

        mVisibleDice = MAX_DICE;

        showDice();

    }

    private void setDiceValue() {
        switch(mDice[0].getImageId()){
            case R.drawable.dice_1:
                DICE_VALUE = 1;

            case R.drawable.dice_2:
                DICE_VALUE = 2;

            case R.drawable.dice_3:
                DICE_VALUE = 3;

            case R.drawable.dice_4:
                DICE_VALUE = 4;

            case R.drawable.dice_5:
                DICE_VALUE = 5;

            case R.drawable.dice_6:
                DICE_VALUE = 6;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        mMenu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    private void showDice() {
        for(int i = 0; i < mVisibleDice; i++) {
            Drawable diceDrawable;
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                diceDrawable = getResources().getDrawable(mDice[i].getImageId(), getApplicationContext().getTheme());
            }
            else {
                diceDrawable = getResources().getDrawable(mDice[i].getImageId());
            }

            mDiceImageViews[0].setImageDrawable(diceDrawable);
            mDiceImageViews[0].setContentDescription(Integer.toString(mDice[0].getNumber()));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        System.out.println(SCORE_TO_WIN);
        switch(item.getItemId()) {
            case R.id.action_one:
                changeDiceVisibility(1);
                showDice();
                return true;

            case R.id.action_stop:
                mTimer.cancel();
                item.setVisible(false);
                return true;

            case R.id.action_roll:
                rollDice();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void rollDice() {
        mMenu.findItem(R.id.action_stop).setVisible(true);

        if(mTimer != null) {
            mTimer.cancel();
        }

        mTimer = new CountDownTimer(2000, 100) {
            @Override
            public void onTick(long l) {
                for(int i = 0; i < mVisibleDice; i++) {
                    mDice[i].roll();
                }
                showDice();
                setDiceValue();
            }

            @Override
            public void onFinish() {
                mMenu.findItem(R.id.action_stop).setVisible(false);
            }
        }.start();
    }

    private void changeDiceVisibility(int numVisible) {
        mVisibleDice = numVisible;

        for(int i = 0; i < numVisible; i++) {
            mDiceImageViews[i].setVisibility(View.VISIBLE);
        }

        for(int i = numVisible; i < MAX_DICE; i++) {
            mDiceImageViews[i].setVisibility(View.GONE);
        }
    }

    private void playGame(EditText playerTally, EditText playerBank, EditText computerTally, EditText computerBank, EditText scoreToWin){
        boolean game = true;
        while(game){
            while(PLAYER_TURN){
                //
            }
            while(COMPUTER_TURN){
                //
            }
        }
    }

}
