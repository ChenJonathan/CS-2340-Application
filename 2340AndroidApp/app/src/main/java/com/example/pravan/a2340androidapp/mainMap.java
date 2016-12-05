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

import java.util.List;

public class mainMap extends FragmentActivity implements OnMapReadyCallback {
    private Model mainModel = Model.instance();
    private GoogleMap mMap;
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
        List<Report> rList = mainModel.getReports();

        for (Report r: rList) {
            double lat = r.getLatitude();
            double lon = r.getLongitude();
            LatLng ltlg = new LatLng(lat, lon);
            mMap.addMarker(new MarkerOptions().position(ltlg).title("Location: " + r.getLocation()
                    + "\n Description: " + r.getDescription()));
        }


    }

    public void addOnClick(View v) {
        //Needs to do worker report depending on user level
        Intent i;
        if (mainModel.getCurrentUser().getAuth() != AuthorizationLevel.USER) {
            i = new Intent(mainMap.this, workerReportController.class);
        } else {
            i = new Intent(mainMap.this, userReportController.class);
        }
        finish();
        startActivity(i);
    }

    public void logoutOnClick(View v) {
        Intent i = new Intent(mainMap.this, Welcome.class);
        finish();
        startActivity(i);

    }


}
