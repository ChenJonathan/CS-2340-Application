package com.example.pravan.a2340androidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;

/**
 * Created by Pravan on 11/26/2016.
 */

public class register extends AppCompatActivity {
    private Intent welc = new Intent(register.this, Welcome.class);

    /**
     * Creates the xml file on screen and makes this class
     * act directly on that xml file
     * @param savedInstanceState not sure
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    /**
     * Sets the registers on click listener
     * so that it checks the text fields for the username
     * and password before referencing it in the database to
     * check availability. Returns to Welcome Screen afterwards
     */
    public void regOnClick() {
        EditText txtUserName = (EditText) findViewById(R.id.txtUserName);
        EditText txtPswrd = (EditText) findViewById(R.id.txtPswrd);
        int checkedRdoBtn = ((RadioGroup) findViewById(R.id.radioGroup)).getCheckedRadioButtonId();
        if (txtPswrd.getText().equals("") || txtUserName.getText().equals("")) {
            //open dialog with an error message
        } else {
            //check availability and add to database

            finish();
            startActivity(welc);
        }
    }

    /**
     * Returns to welcome screen
     */
    public void cancelOnClick() {
        finish();
        startActivity(welc);
    }
}
