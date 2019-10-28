/* Jeremy Holloway
 * CPSC-4150-001
 * 10/11/2019
 * Homework 3
 * C20581376
 * jjhollo@g.clemson.edu
 */
package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    /**
     * This is the creation function for the view
     * @param savedInstanceState is any saved variables for this instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null) {

            setContentView(R.layout.activity_main);

            //get fragment for recycler view
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment fragment = fragmentManager.findFragmentById(R.id.recycle_container);

            if (fragment == null) {
                fragment = new LocationFragment();
                fragment.setRetainInstance(true);
                fragmentManager.beginTransaction().add(R.id.fragment, fragment).commit();
            } else {
                fragment.setRetainInstance(true);
                fragmentManager.beginTransaction().add(R.id.fragment, fragment).commit();

            }
        } else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment fragment = fragmentManager.findFragmentById(R.id.recycle_container);
            fragmentManager.beginTransaction().add(R.id.fragment, fragment).commit();
        }

        //set the dark sky button
        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param view
             */
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://darksky.net/poweredby/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        //set the icon button
        Button button1 = findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param view
             */
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://icons8.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
}
