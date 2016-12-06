package com.example.pravan.a2340androidapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.renderscript.Double2;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by Pravan on 11/26/2016.
 */

public class workerReportController extends AppCompatActivity {

    private String[] waterType = {"Bottled", "Well", "Stream", "Lake", "Spring", "Other"};
    private String[] waterCond = {"Portable", "Treatable-Muddy", "Treatable-Clear", "Waste"};
    ListDB listDB = ListDB.getInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_report);

        Spinner spnWaterType = (Spinner) findViewById(R.id.spnWaterType);
        Spinner spnWaterCond = (Spinner) findViewById(R.id.spnWaterCond);

        ArrayAdapter<String> waterTypeAdapt = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, waterType);
        spnWaterType.setAdapter(waterTypeAdapt);

        ArrayAdapter<String> waterCondAdapt = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, waterCond);
        spnWaterCond.setAdapter(waterCondAdapt);

    }

    public void addOnClick(View v) {
        EditText location = ((EditText) findViewById(R.id.txtLocation));
        EditText description = (EditText) findViewById(R.id.txtDescription);
        Spinner spnWaterType = (Spinner) findViewById(R.id.spnWaterType);
        Spinner spnWaterCond = (Spinner) findViewById(R.id.spnWaterCond);
        EditText txtVPPM = (EditText) findViewById(R.id.txtVPPM);
        EditText txtCPPM = (EditText) findViewById(R.id.txtCPPM);
        EditText txtLat = (EditText) findViewById(R.id.txtLat);
        EditText txtLon = (EditText) findViewById(R.id.txtLong);

        double vppm = 0.0;
        double cppm = 0.0;
        double lat = 0.0;
        double lon = 0.0;


        if (location.getText().equals("") || txtLat.getText().toString().matches("")
                || txtCPPM.getText().toString().matches("") || txtLon.getText().toString().matches("")
                || txtVPPM.getText().toString().matches("")) {

            //display dialog box
            new AlertDialog.Builder(workerReportController.this).setTitle("Add Report Error")
                    .setMessage("Not all Fields have been used")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
        } else {
            //add to database
            try {
                vppm = Double.parseDouble(txtVPPM.getText().toString());
                cppm = Double.parseDouble(txtCPPM.getText().toString());
                lat = Double.parseDouble(txtLat.getText().toString());
                lon = Double.parseDouble(txtLon.getText().toString());

                if (lat < -90 || lat > 90 || lon < -90 || lon > 90) {
                    new AlertDialog.Builder(workerReportController.this).setTitle("Add Report Error")
                            .setMessage("Latitude and Longitude need to be between -90 and 90")
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();
                } else {
                    Report r = new WorkerReport(location.getText().toString(), lat, lon,
                            description.getText().toString(), listDB.getCurrentUser().getName(),
                            spnWaterType.getSelectedItem().toString(), spnWaterCond.getSelectedItem().toString(),
                            vppm, cppm);

                    listDB.addReport(r);

                    Intent i = new Intent(workerReportController.this, mainMap.class);
                    finish();
                    startActivity(i);
                }

            } catch (NumberFormatException nfe) {
                //display dialog box
                new AlertDialog.Builder(workerReportController.this).setTitle("Add Report Error")
                        .setMessage("Latitude, Longitude, Virus PPM, and Contaminant PPM need to be numbers")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
            }

        }

    }

    public void cancelOnClick(View v) {
        Intent i = new Intent(workerReportController.this, mainMap.class);
        finish();
        startActivity(i);
    }
}
