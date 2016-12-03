package com.example.pravan.a2340androidapp;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class mainMap extends FragmentActivity implements OnMapReadyCallback {
    private double latitude;
    private double longitude;
    private GoogleMap mMap;
    private boolean gone = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        FrameLayout rlMap = (FrameLayout) findViewById(R.id.map);
        final Button btnAdd = (Button) findViewById(R.id.btnAdd);

        //In order to get the User from the previous class
        //use: getIntent().getSerializableExtra("User");
        //where "User" is the tag assigned to it in the previous
        //class

        //Add all the reports to the map
        //for x in list of reports
        //  marker = new google.maps.Marker({
        //      get all the details of the reports
        //  });

        //This is for the listener on the markers
        // google.maps.event.addListener(marker, "click", function(event) {
        //      var longi

        //These two methods might need to be combined

        /**
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                //Use these to pass into the report
                latitude = latLng.latitude;
                longitude = latLng.longitude;
            }
        });
         */
        rlMap.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    if (gone == false) {
                        btnAdd.setVisibility(View.GONE);
                        gone = true;
                    } else {
                        btnAdd.setX(motionEvent.getX());
                        btnAdd.setY(motionEvent.getY());
                        btnAdd.setVisibility(View.VISIBLE);
                        gone = false;
                    }
                }
                return true;
            }
        });


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void addOnClick(View v) {
        //Needs to do worker report depending on user level
        Intent i = new Intent(mainMap.this, userReportController.class);
        String latLong = latitude + ", " + longitude;
        i.putExtra("Latitude, Longitude", latLong);
        finish();
        startActivity(i);
    }

    public void logoutOnClick(View v) {
        Intent i = new Intent(mainMap.this, Welcome.class);
        finish();
        startActivity(i);

    }


}
