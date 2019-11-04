package com.example.timezones;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends WearableActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Local variables
        TextView localTimeDisplay;
        TextView laTimeDisplay;
        TextView londonTimeDisplay;
        TextView hongKongTimeDisplay;
        final int LA_TIME_OFFSET = -3;
        final int LONDON_TIME_OFFSET = 4;
        final int HONG_KONG_TIME_OFFSET = 12;

        // Connect TextView objects with xml TextViews
        localTimeDisplay = findViewById(R.id.localTime);
        // TODO: set up the other 3 TextViews as shown above

        // Get the Current Date
        Date myDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(myDate);

        // Pull out the current hour and minutes to be manipulated in order to calculate the time
        // for other Time Zones
        int localHour = calendar.get(Calendar.HOUR_OF_DAY);
        int localMinutes = calendar.get(Calendar.MINUTE);
        String localMin;

        // Append a zero before the minutes if minutes are less than 10. This is purely a
        // formatting step for when the time is displayed to the user
        if(localMinutes < 10){
            localMin = "0" + localMinutes;
        }else{
            localMin = Integer.toString(localMinutes);
        }

        // Calculate current time for individual time zones
        String localTime = localHour + ":" + localMin;
        // TODO: Create a string with the hour and minutes of the other three city's times
        // (Hint: use the getRegionalHour function to calculate an appropriate hour)

        // Display each time zone
        localTimeDisplay.setText(localTime);
        // TODO: Set the Text to be displayed for the other 3 cities

        // Enables Always-on
        setAmbientEnabled();
    }   //end onCreate method

    /**
     * Functionality: Calculate the hour of the time zone you want to access and format it in
     *                Military Time (between 0 - 24)
     * @param local_hour = the current hour for your time zone
     * @param offset = the difference between your local hour and the hour of the time zone you
     *               want to access
     * @return hour = the current hour of the time zone you want to access
     */
    public int getRegionalHour(int local_hour, int offset){
        int hour = 0;

        // TODO: add the logic to calculate an appropriate hour to return
        /* Consider this:
            If the local time is 23:00 (11pm), then the time in London would be 4 hours past that.
            As such we cannot simply add the offset to the current hour to get 03:00 (3am).
            In the same way, if the local time was 02:00 (2am), then the time in Los Angeles would
                be 3 hours behind that.

            In this function, include the logic to get an appropriate hour to return to the user
            depending on the local time's hour (local_hour) and the time difference between the
            local hour and the new city's local hour (offset)
         */

        return hour;
    }   //end getNewTime method
}   //end MainActivity class
