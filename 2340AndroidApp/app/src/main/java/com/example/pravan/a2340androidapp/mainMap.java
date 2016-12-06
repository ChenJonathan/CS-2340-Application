package com.example.pravan.a2340androidapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class mainMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ListDB listDB = ListDB.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        


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
        List<Report> list = listDB.getRepList();
        for (Report r: list) {
            LatLng ltlg = new LatLng(r.getLatitude(), r.getLongitude());
            mMap.addMarker(new MarkerOptions().position(ltlg).title(r.getLocation() + "\n"
                        + r.getDescription() + "\n"));
        }
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void addOnClick(View v) {
        //Needs to do worker report depending on user level
        if (listDB.getCurrentUser().getAuth() != AuthorizationLevel.USER) {
            Intent i = new Intent(mainMap.this, workerReportController.class);
            finish();
            startActivity(i);
        } else {
            Intent i = new Intent(mainMap.this, userReportController.class);
            finish();
            startActivity(i);
        }
    }

    public void logoutOnClick(View v) {
        Intent i = new Intent(mainMap.this, Welcome.class);
        finish();
        startActivity(i);

    }

    public void searchOnClick(View v) {
        EditText txtLat = (EditText) findViewById(R.id.txtLat);
        EditText txtLon = (EditText) findViewById(R.id.txtLon);

        if (txtLat.getText().toString().matches("") || txtLon.getText().toString().matches("")) {
            new AlertDialog.Builder(mainMap.this).setTitle("Search Error")
                    .setMessage("Please Enter a Latitude and Longitude.")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
        } else {
            double lat = Double.parseDouble(txtLat.getText().toString());
            double lon = Double.parseDouble(txtLon.getText().toString());

            if (lat > 90 || lat < -90 || lon > 90 || lon < -90) {
                new AlertDialog.Builder(mainMap.this).setTitle("Search Error")
                        .setMessage("Latitude and Longitude have to be between -90 and 90.")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
            } else {
                LatLng ltlg = new LatLng(lat, lon);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(ltlg));
            }
        }
    }

    public void profileOnClick(View v) {
        Intent i = new Intent(mainMap.this, ProfileController.class);
        finish();
        startActivity(i);
    }


}
