/* Jeremy Holloway
 * CPSC-4150-001
 * 9/24/2019
 * HW2
 * C20581376
 * jjhollo@g.clemson.edu
 */
package com.example.pigdice;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    /**
     * This function overrides the onCreate function
     * @pre class must be instantiated
     * @post main activity is created
     * @param savedInstanceState = holds the instance variables
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button button = findViewById(R.id.PlayButton);
        button.setOnClickListener(new View.OnClickListener() {
            /**
             * This function overrides the onclick method
             * @pre button must be instantiated with a new listener
             * @post the game activity is started
             * @param view = the current view
             */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Game.class);
                startActivity(intent);
            }
        });

        Button button1 = findViewById(R.id.about_page);
        button1.setOnClickListener(new View.OnClickListener() {
            /**
             * This fucntion overrides the onclick method
             * @pre button must be instantiated with a new listener
             * @post the aboutPigDice activity is started
             * @param view = the current view
             */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AboutPigDice.class);
                startActivity(intent);
            }
        });
    }

}
