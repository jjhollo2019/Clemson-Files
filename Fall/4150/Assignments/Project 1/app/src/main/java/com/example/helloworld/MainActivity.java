/* Jeremy Holloway
 * CPSC-4150-001
 * Project 1
 * 9/6/2019
 */
package com.example.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * This function is the main driver of the class
 * @pre The class must be properly instantiated
 * @post The class is destroyed
 */
public class MainActivity extends Activity  {
    //Declare imageview, button, and spinner
    ImageView pic;
    Button button;
    Spinner spinner;
    int index = 0;

    @Override
    /**
     * This function defines what should happen when the class view is created
     * @pre The class must be instantiated
     * @param savedInstanceState is the state of the function at any given moment
     * @post The class destructor is called
     */
    protected void onCreate(Bundle savedInstanceState){
        //call the super class
        super.onCreate(savedInstanceState);
        //set the layout
        setContentView(R.layout.activity_main);

        //set spinner
        spinner = findViewById(R.id.font_spinner);
        //declare and init array adapter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.fonts, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //override listener
        spinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            /**
             * This function overrides the onItemSelected function to change the text color
             * @pre onCreate must be called
             * @param parent provides extra content for the current view
             * @param view is the current view
             * @param pos is the position is the list item position that was selected
             * @param id is the caller id field
             * @post The corresponding item case is executed
             */
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                //grab the item selected
                String colorSelect = ((String) parent.getItemAtPosition(pos));
                //switch on the color selected
                switch (colorSelect) {
                    case ("Red"): {
                        //find view
                        TextView textView = findViewById(R.id.textView);
                        //set color to red
                        textView.setTextColor(Color.parseColor("#FF0000"));
                        break;
                    }
                    case "Green": {
                        //find view
                        TextView textView = findViewById(R.id.textView);
                        //set color to green
                        textView.setTextColor(Color.parseColor("#00FF00"));
                        break;
                    }
                    case "Blue": {
                        //find view
                        TextView textView = findViewById(R.id.textView);
                        //set color to blue
                        textView.setTextColor(Color.parseColor("#0000FF"));
                        break;
                    }
                    case "Yellow": {
                        //find view
                        TextView textView = findViewById(R.id.textView);
                        //set color to yellow
                        textView.setTextColor(Color.parseColor("#FFFF00"));
                        break;
                    }
                    case "Default": {
                        //find view
                        TextView textView = findViewById(R.id.textView);
                        //set default color scheme
                        textView.setTextColor(Color.parseColor("#F66733"));
                        break;
                    }
                }
            }
            //set to default when not in use
            @Override
            /**
             * This function overrides the onNothingSelected function to select the default color
             * @pre The onCreate function must be called
             * @param parent gives extra context to the view
             * @post The default color will be set
             */
            public void onNothingSelected(AdapterView<?> parent){
                //find view
                TextView textView = findViewById(R.id.textView);
                //set to default color scheme
                textView.setTextColor(Color.parseColor("#F66733"));
            }
        });

        //find the picture
        pic = findViewById(R.id.ImageView1);

        //create a new listener
        pic.setOnClickListener(new View.OnClickListener() {
            //override the onClick method to swap pictures
            @Override
            /**
             * This function will override the onClick method to change the imageView picture
             * @pre The onCreate method must be called
             * @param view is the view currently in use
             * @post The image in the imageView is set to a different image
             */
            public void onClick(View view) {
                //increment the image index
                index++;
                //switch in the index
                switch ((index % 5)){
                    case 0: {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            Resources.Theme theme = getApplicationContext().getTheme();
                            pic.setImageDrawable(getResources().getDrawable(R.drawable.pic2, theme));
                            break;
                        }
                        //else use deprecated method
                        else {
                            pic.setImageDrawable((getResources().getDrawable(R.drawable.pic2)));
                            break;
                        }
                    }
                    case 1: {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            Resources.Theme theme = getApplicationContext().getTheme();
                            pic.setImageDrawable(getResources().getDrawable(R.drawable.pic3, theme));
                            break;
                        }
                        //else use deprecated method
                        else {
                            pic.setImageDrawable((getResources().getDrawable(R.drawable.pic3)));
                            break;
                        }
                    }
                    case 2: {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            Resources.Theme theme = getApplicationContext().getTheme();
                            pic.setImageDrawable(getResources().getDrawable(R.drawable.pic4, theme));
                            break;
                        }
                        //else use deprecated method
                        else {
                            pic.setImageDrawable((getResources().getDrawable(R.drawable.pic4)));
                            break;
                        }
                    }
                    case 3: {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            Resources.Theme theme = getApplicationContext().getTheme();
                            pic.setImageDrawable(getResources().getDrawable(R.drawable.pic5, theme));
                            break;
                        }
                        //else use deprecated method
                        else {
                            pic.setImageDrawable((getResources().getDrawable(R.drawable.pic5)));
                            break;
                        }
                    }
                    case 4: {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            Resources.Theme theme = getApplicationContext().getTheme();
                            pic.setImageDrawable(getResources().getDrawable(R.drawable.pic1, theme));
                            break;
                        }
                        //else use deprecated method
                        else {
                            pic.setImageDrawable((getResources().getDrawable(R.drawable.pic1)));
                            break;
                        }
                    }
                }
            }
        });

        //find the button id
        button = findViewById(R.id.button);

        //set a new onClick listener
        button.setOnClickListener(new View.OnClickListener() {
            //override the onClick method
            @Override
            /**
             * This function overrides the onClick method to start a new activity
             * @pre The onCreate function must be called
             * @param view is the currently used view
             * @post The new activity will be started
             */
            public void onClick(View view) {
                //set the intent to switch activities
                Intent intent = new Intent(MainActivity.this, changeView.class);
                //start activity
                startActivity(intent);
            }
        });
    }
}


