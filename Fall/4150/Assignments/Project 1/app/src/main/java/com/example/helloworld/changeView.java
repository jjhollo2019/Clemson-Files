/* Jeremy Holloway
 * CPSC-4150-001
 * Project 1
 * 9/6/2019
 */
package com.example.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class changeView extends Activity {
    //Declare buttons
    Button button;
    Button button2;

    @Override
    /**
     * This function defines what should happen when the class view is created
     * @pre The class must be instantiated
     * @param savedInstanceState is the state of the function at any given moment
     * @post The class destructor is called
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set the layout
        setContentView(R.layout.activity_change_view);

        //find the return button
        button = findViewById(R.id.button2);

        //attach a new listener
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * This function overrides the onClick method to start a new activity
             * @pre The onCreate function must be called
             * @param view is the currently used view
             * @post The new activity will be started
             */
            public void onClick(View view) {
                //set the intent
                Intent intent = new Intent(changeView.this, MainActivity.class);
                //start new activity
                startActivity(intent);
            }
        });

        //find the hyperlink button
        button2 = findViewById(R.id.button3);

        //attach a new listener
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * This function overrides the onClick method to start a new activity
             * @pre The onCreate function must be called
             * @param view is the currently used view
             * @post The new activity will be started
             */
            public void onClick(View view) {
                //set the intent to view a web page
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.linkedin.com/in/jeremy-holloway/"));
                //start activity
                startActivity(intent);
            }
        });
    }
}
