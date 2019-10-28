/* Jeremy Holloway
 * CPSC-4150-001
 * 10/11/2019
 * Homework 3
 * C20581376
 * jjhollo@g.clemson.edu
 */
package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

public class DetailView extends AppCompatActivity {

    //data for detailview
    private static double temperature;
    private static double humidity;
    private static double windSpeed;
    private static double windGust;
    private static double chanceRain;
    private static int imageID;
    private static String Location;

    /**
     * This function will set the data items for the class
     * @param jsonObject is the retrieved JSON object
     * @param image is the resource ID for the image icon
     * @param place is the string for the location name
     */
    public void setDetailView(JSONObject jsonObject, int image, String place) {
        try {
            temperature = jsonObject.getDouble("temperature");
            humidity = jsonObject.getDouble("humidity") * 10.0;
            windSpeed = jsonObject.getDouble("windSpeed");
            windGust = jsonObject.getDouble("windGust");
            chanceRain = jsonObject.getDouble("precipProbability") * 10.0;
            imageID = image;
            Location = place;
        } catch (Exception e) {
            Log.e("DETAILVIEW", "Error setting DetailView");
        }
    }

    /**
     * This is the creation function for the class
     * @param savedInstanceState is saved variables for the instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        //set button to go back
        Button button = findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param view
             */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailView.this.getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        //set location name
        TextView name = findViewById(R.id.nameView6);
        name.setText(Location);

        //set icon
        ImageView imageView = findViewById(R.id.iconView);
        imageView.setImageResource(imageID);

        //set temperature
        TextView temp = findViewById(R.id.tempView);
        String t = temperature + "\u00B0";
        temp.setText(t);

        //set humidity
        TextView humid = findViewById(R.id.humidView3);
        String Htext = "Humidity: " + (int) humidity;
        humid.setText(Htext);

        //set wind speed
        TextView wSpeed = findViewById(R.id.wspeedView4);
        String wText = "Wind Speed: " + windSpeed + " mph";
        wSpeed.setText(wText);

        //set wind gust speed
        TextView wGust = findViewById(R.id.gustView2);
        String gust = "Wind Gust: " + windGust + " mph";
        wGust.setText(gust);

        //set chance of rain
        TextView cRain = findViewById(R.id.chanceView5);
        String chance = "Chance of Rain: " + chanceRain + "%";
        cRain.setText(chance);

    }
}
