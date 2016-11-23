package com.example.pravan.a2340androidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class Welcome extends AppCompatActivity {

    /**
     * Opens up the fxml file and also creates
     * listeners for all the buttons
     * @param savedInstanceState tbh not sure
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        //Creates a button and its on click listener
        final Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Open Login Dialogue
                RelativeLayout rlLogin = (RelativeLayout) findViewById(R.id.login);
                rlLogin.setVisibility(View.VISIBLE);
            }
        });
    }
}
