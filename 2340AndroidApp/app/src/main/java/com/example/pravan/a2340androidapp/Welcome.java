package com.example.pravan.a2340androidapp;

import android.content.Intent;
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
        //final RelativeLayout rlWelc = (RelativeLayout) findViewById(R.id.activity_welcome);

        //Creates login buttons on click listener
        final Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Open Login Dialogue
                Intent i = new Intent(Welcome.this, login.class);
                finish();
                startActivity(i);
            }
        });

        //Creates register buttons on click function
        final Button btnReg = (Button) findViewById(R.id.btnReg);
        btnReg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Welcome.this, register.class);
                finish();
                startActivity(i);
            }
        });


    }
}
