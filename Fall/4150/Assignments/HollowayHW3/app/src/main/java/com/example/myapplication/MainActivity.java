package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.weather.WeatherObj;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static RecyclerView recyclerView;
    private static RecyclerView.Adapter mAdapter;
    private static RecyclerView.LayoutManager layoutManager;

    private static List<WeatherObj> places;


    public static void setLocations() {
        WeatherObj gVille = new WeatherObj("Greenville", 34.8332339,-82.3979357);
        places.add(0, gVille);

        WeatherObj chi = new WeatherObj("Chicago", 41.8339032,-87.8723901);
        places.add(0, chi);

        WeatherObj tokyo = new WeatherObj("Tokyo", 35.5090563,139.2080125);
        places.add(0, tokyo);

        WeatherObj sasebo = new WeatherObj("Sasebo", 33.1966845,129.323942);
        places.add(0,sasebo);

        WeatherObj phuket = new WeatherObj("Phuket", 7.8309249,98.079054);
        places.add(0, phuket);

        WeatherObj puertoPrincessa = new WeatherObj("Puerto Princessa", 9.9148608,118.4702694);
        places.add(0, puertoPrincessa);

        WeatherObj vlad = new WeatherObj("Vladivostok", 43.1738705,131.8954111);
        places.add(0, vlad);

        WeatherObj chin = new WeatherObj("Cinhae", 35.1011177,128.7502268);
        places.add(0, chin);

        WeatherObj singa = new WeatherObj("Singapore", 1.3143394,103.703822);
        places.add(0, singa);

        WeatherObj banga = new WeatherObj("Bangaladesh", 23.4955528,88.0951811);
        places.add(0, banga);
    }

    private void setCurrent(Location location) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            for ( Address address: addresses) {
                System.out.println(address.toString());
            }
        } catch (IOException e) {
            Log.e("Main", "Error retrieving current location");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.list);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MyItemRecyclerViewAdapter(places);
        recyclerView.setAdapter(mAdapter);

        FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(this);
        client.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    setCurrent(location);
                }
            }
        });

        places = new ArrayList<WeatherObj>();

        setLocations();

    }


}
