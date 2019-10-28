package com.example.myapplication.weather;

import android.net.Uri;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class WeatherObj {
    public String name;
    private double mLatitude;
    private double mLongitude;
    private double temp;
    private String summary;
    private final String TAG = "Weather Object";
    private final static String APIKey = "f24320518f5f7e35fd110b90465143d9";
    private final static String requestURL = "https://api.darksky.net/forecast/";

    public WeatherObj(String loc, double latitude, double longitude) {
        name = loc;
        mLatitude = latitude;
        mLongitude = longitude;


        String url = Uri.parse(requestURL).buildUpon().appendQueryParameter("key", APIKey).appendQueryParameter("latitude", String.valueOf(mLatitude)).appendQueryParameter("longitude", String.valueOf(mLongitude)).build().toString();

        final JsonObjectRequest requestObj = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                parseJsonObj(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //TODO
            }
        });
    }

    private void parseJsonObj(JSONObject json) {
        try {
            temp = json.getDouble("currently:temperature");
            summary = json.getString("currently:summary");

        } catch (Exception e) {
            Log.e(TAG, "One or more fields not found in the JSON data");
        }
    }
}
