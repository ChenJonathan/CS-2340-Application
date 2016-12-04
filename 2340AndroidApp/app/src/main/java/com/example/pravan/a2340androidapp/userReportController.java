package com.example.pravan.a2340androidapp;

import android.content.DialogInterface;
import android.content.Intent;
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

public class userReportController extends AppCompatActivity {

    private String[] waterType = {"Bottled", "Well", "Stream", "Lake", "Spring", "Other"};
    private String[] waterCond = {"Portable", "Treatable-Muddy", "Treatable-Clear", "Waste"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_report);

        Spinner spnWaterType = (Spinner) findViewById(R.id.spinner4);
        Spinner spnWaterCond = (Spinner) findViewById(R.id.spinner6);

        ArrayAdapter<String> waterTypeAdapt = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, waterType);
        spnWaterType.setAdapter(waterTypeAdapt);

        ArrayAdapter<String> waterCondAdapt = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, waterCond);
        spnWaterCond.setAdapter(waterCondAdapt);
    }

    public void addOnClick(View v) {
        EditText location = ((EditText) findViewById(R.id.txtLocation));
        EditText description = (EditText) findViewById(R.id.editText2);
        Spinner spnWaterType = (Spinner) findViewById(R.id.spinner4);
        Spinner spnWaterCond = (Spinner) findViewById(R.id.spinner6);

        if (location.getText().toString().matches("") || spnWaterCond.getSelectedItem().toString().matches("")
                || spnWaterType.getSelectedItem().toString().matches("")
                || description.getText().toString().matches("")) {

            //display dialog box
            new AlertDialog.Builder(userReportController.this).setTitle("Add Report Error")
                    .setMessage("Not all Fields have been used")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();

        } else {
            //add to database

            Intent i = new Intent(userReportController.this, mainMap.class);
            finish();
            startActivity(i);
        }

    }

    public void cancelOnClick(View v) {
        Intent i = new Intent(userReportController.this, mainMap.class);
        finish();
        startActivity(i);
    }
}
