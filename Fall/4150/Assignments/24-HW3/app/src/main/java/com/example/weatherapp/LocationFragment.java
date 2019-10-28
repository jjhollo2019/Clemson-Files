package com.example.weatherapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.weatherapp.Weather.WeatherObj;
import java.util.ArrayList;
import java.util.List;

//The pattern for this class was taken from the zybook activity Band App
public class LocationFragment extends Fragment {

    //list of places
    private static List<WeatherObj> places;

    /**
     * This function will set the locations for the recycler view
     * @param context is the context of the main activity for the object request queue
     */
    public static void setLocations(Context context) {
        //init the list
        places = new ArrayList<>();
        //add locations
        WeatherObj gVille = new WeatherObj("Greenville", 34.8332339,-82.3979357, context);
        places.add(0, gVille);

        WeatherObj chi = new WeatherObj("Chicago", 41.8339032,-87.8723901, context);
        places.add(0, chi);

        WeatherObj wies = new WeatherObj("Wiesbaden", 50.0725716,8.1781423, context);
        places.add(0, wies);

        WeatherObj heid = new WeatherObj("Heidelberg", 49.4058188,8.6134031, context);
        places.add(heid);

        WeatherObj tokyo = new WeatherObj("Tokyo", 35.5090563,139.2080125, context);
        places.add(0, tokyo);

        WeatherObj sasebo = new WeatherObj("Sasebo", 33.1966845,129.323942, context);
        places.add(0,sasebo);

        WeatherObj fuk = new WeatherObj("Fukuoka", 33.6501814,130.1235452, context);
        places.add(0, fuk);

        WeatherObj phuket = new WeatherObj("Phuket", 7.8309249,98.079054, context);
        places.add(0, phuket);

        WeatherObj puertoPrincessa = new WeatherObj("Puerto Princessa", 9.9148608,118.4702694, context);
        places.add(0, puertoPrincessa);

        WeatherObj vlad = new WeatherObj("Vladivostok", 43.1738705,131.8954111, context);
        places.add(0, vlad);

        WeatherObj chin = new WeatherObj("Chinhae", 35.1011177,128.7502268, context);
        places.add(0, chin);

        WeatherObj singa = new WeatherObj("Singapore", 1.3143394,103.703822, context);
        places.add(0, singa);

        WeatherObj banga = new WeatherObj("Bangaladesh", 23.4955528,88.0951811, context);
        places.add(0, banga);

        WeatherObj batam = new WeatherObj("Batam", 0.8378426,103.7722403, context);
        places.add(0, batam);
    }

    /**
     * This function will create the view for the class
     * @param inflater is the inflator for the class layout
     * @param container is the view group for the class
     * @param savedInstanceState is saved variables for the state
     * @return the view for the class
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location_list, container, false);

        //get and set recycler view
        RecyclerView recyclerView = view.findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //set the locations
        setLocations(getContext());
        //set the adapter
        LocationAdapter adapter = new LocationAdapter(places);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private class LocationHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private WeatherObj mLocation;
        private TextView mTitleTextView;
        private TextView mContentTextView;
        private ImageView mImageView;

        /**
         * This function sets the view variables
         * @param inflater inflates the item view
         * @param parent is the view from the class
         */
        public LocationHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fragment_location, parent, false));
            itemView.setOnClickListener(this);
            //set view text and image fields
            mTitleTextView = itemView.findViewById(R.id.item_title);
            mContentTextView = itemView.findViewById(R.id.item_content);
            mImageView = itemView.findViewById(R.id.weatherIcon);
        }

        /**
         * This function sets the view text and image ID
         * @param obj is the weather object to be modified
         */
        public void bind(WeatherObj obj) {
            obj.setWeather(obj.getJsonObject());
            obj = obj.getObj();
            //set object
            mLocation = obj;
            //mLocation.setWeather(mLocation.getJsonObject());
            //set text
            mTitleTextView.setText(mLocation.getName());
            mContentTextView.setText(mLocation.getSummary());
            //set image
            mImageView.setImageResource(mLocation.getImageID());
        }

        /**
         * This function handles the onClick method for the view
         * @param view is the view from the parent class
         */
        public void onClick(View view) {
            //make new detail view
            DetailView detailView = new DetailView();
            //set variables
            detailView.setDetailView(mLocation.getJsonObject(), mLocation.getImageID(), mLocation.getName());
            //start detail view
            Intent intent = new Intent(LocationFragment.this.getContext(), DetailView.class);
            startActivity(intent);
        }
    }

    private class LocationAdapter extends RecyclerView.Adapter<LocationHolder> {

        //list of weather objects
        private List<WeatherObj> mValues;

        /**
         * This function sets the list variable for the class
         * @param items is a list of weather objects from the parent class
         */
        public LocationAdapter(List<WeatherObj> items) { mValues = items; }

        /**
         * This function creates the view for the item
         * @param parent is the parent view
         * @param viewType is the type of view
         * @return a new location holder
         */
        @Override
        public LocationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new LocationHolder(layoutInflater, parent);
        }

        /**
         * This function will bind objects to the view
         * @param holder the holder for the class
         * @param position the index of the item
         */
        @Override
        public void onBindViewHolder(final LocationHolder holder, int position) {
            holder.bind(mValues.get(position));
        }

        /**
         * This function will return the number of items in the list
         * @return the number of items in the list
         */
        @Override
        public int getItemCount() {
            if ( mValues != null){
                return mValues.size();
            }
            else
                return 0;
        }
    }
}
