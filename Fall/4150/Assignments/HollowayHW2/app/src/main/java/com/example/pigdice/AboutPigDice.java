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

public class AboutPigDice extends AppCompatActivity {

    /**
     * This function brings up the about pig dice section from the home menu
     * @pre The main activity must be running
     * @post the user is returned to the home screen
     * @param savedInstanceState = saved instance variables
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_pig_dice);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button button = findViewById(R.id.return_menu);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutPigDice.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
