/* Jeremy Holloway
 * CPSC-4150-001
 * 10/11/2019
 * Homework 3
 * C20581376
 * jjhollo@g.clemson.edu
 */
package com.example.weatherapp.Weather;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.weatherapp.R;
import org.json.JSONObject;;

public class WeatherObj {

    //data for the weather object
    private String name;
    private String summary;
    private double mLatitude;
    private double mLongitude;
    private final String TAG = "Weather Object";
    private final static String requestURL = "https://api.darksky.net/forecast/f24320518f5f7e35fd110b90465143d9/";
    private int mImageResource;
    private JSONObject jsonObject;
    private RequestQueue queue;

    /**
     * This is the class constructor
     * @param loc is the name of the location
     * @param latitude is the latitude of the location
     * @param longitude is the longitude of the location
     * @param context is the context of the main activity
     */
    public WeatherObj(String loc, double latitude, double longitude, Context context) {
        //set data, call API and update the object
        name = loc;
        mLatitude = latitude;
        mLongitude = longitude;
        queue = Volley.newRequestQueue(context);
        //make weather request
        updateWeather();
        //update variables
        setWeather(jsonObject);
    }

    public WeatherObj getObj() { return  this; }

    /**
     * This is a getter for the JSON object
     * @return the JSON object for the current location
     */
    public JSONObject getJsonObject() { return jsonObject; }

    /**
     * This is a function to make the API request and pass the response to a setter function
     */
    public void updateWeather(){
        try {
            //build url to call
            String url = Uri.parse(requestURL + mLatitude + "," + mLongitude).buildUpon().build().toString();

            //make request
            final JsonObjectRequest requestObj = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    //parse result
                    Log.d( "API RESPONSE", response.toString());
                    parseJsonObj(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //log error
                    Log.e("weather object: ", "error on parsing");
                }
            });
            //add to queue for processing
            queue.add(requestObj);
        } catch (Exception e) {
            Log.e(TAG, "Error making GET request");
        }
    }

    /**
     * This function will parse the json object for the current object
     * @param json
     */
    private void parseJsonObj(JSONObject json) {
        try {
            //get the current object from the response
            jsonObject = json.getJSONObject("currently");
            setWeather(jsonObject);
        } catch (Exception e) {
            Log.e(TAG, "One or more fields not found in the JSON data");
        }
    }

    /**
     * This function will set the weather for the location
     * @param object is the current weather object
     */
    public void setWeather(JSONObject object) {
        try {
            //get summary and icon
            summary = object.getString("summary");
            setImage(object.getString("icon"));
        } catch (Exception e) {
            Log.e("SET_WEATHER", "Error setting the weather");
        }
    }

    /**
     * This function is used to set the icon id based the string returned by the API
     * @param icon is a string for the function to switch on and set the resource ID
     */
    public void setImage(String icon) {
        //switch on the text for the icon returned by the API
        switch(icon){
            case "clear-day":
                mImageResource = R.drawable.sunny;
                break;

            case "clear-night":
                mImageResource = R.drawable.clear_night;
                break;

            case "rain":
                mImageResource = R.drawable.rain;
                break;

            case "snow":
                mImageResource = R.drawable.snow;
                break;

            case "sleet":
                mImageResource = R.drawable.sleet;
                break;

            case "wind":
                mImageResource = R.drawable.clouds;
                break;

            case "fog":
                mImageResource = R.drawable.fog;
                break;

            case "cloudy":
                mImageResource = R.drawable.clouds;
                break;

            case "partly-cloudy-day":
                mImageResource = R.drawable.cloudy_day;
                break;

            case "partly-cloudy-night":
                mImageResource = R.drawable.cloudy_night;
                break;

            case "hail":
                mImageResource = R.drawable.hail;
                break;

            case "thunderstorm":
                mImageResource = R.drawable.stormy_weather;
                break;
        }
    }

    /**
     * This is a getter function for the location name
     * @return the location name
     */
    public String getName() { return name; }

    /**
     * This is a getter function for the summary text
     * @return the summary from the weather API
     */
    public String getSummary() { return summary; }

    /**
     * Thsi is a getter function for the image resource ID
     * @return the image resource ID
     */
    public int getImageID() { return mImageResource; }
}
