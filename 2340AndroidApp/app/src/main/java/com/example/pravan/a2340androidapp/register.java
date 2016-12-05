package com.example.pravan.a2340androidapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.google.android.gms.auth.api.Auth;

/**
 * Created by Pravan on 11/26/2016.
 */

public class register extends AppCompatActivity {

    private Model mainModel = Model.instance();
    /**
     * Creates the xml file on screen and makes this class
     * act directly on that xml file
     * @param savedInstanceState not sure
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    /**
     * Sets the registers on click listener
     * so that it checks the text fields for the username
     * and password before referencing it in the database to
     * check availability. Returns to Welcome Screen afterwards
     */
    public void regOnClick(View v) {
        EditText txtUserName = (EditText) findViewById(R.id.txtUserName);
        EditText txtPswrd = (EditText) findViewById(R.id.txtPswrd);
        int checkedRdoBtn = ((RadioGroup) findViewById(R.id.radioGroup)).getCheckedRadioButtonId();

        if (txtPswrd.getText().toString().matches("") || txtUserName.getText().toString().matches("")) {
            //open dialog with an error message
            new AlertDialog.Builder(register.this).setTitle("Register Error")
                    .setMessage("Please Enter a Username and Password")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
        } else {
            //check availability and add to database
            if (mainModel.checkUserExists(txtUserName.getText().toString())) {
                new AlertDialog.Builder(register.this).setTitle("Register Error")
                        .setMessage("Username is taken")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
            } else {
                AuthorizationLevel auth;
                if (checkedRdoBtn == 0){
                    auth = AuthorizationLevel.USER;
                } else if (checkedRdoBtn == 1) {
                    auth = AuthorizationLevel.WORKER;
                } else if (checkedRdoBtn == 2) {
                    auth = AuthorizationLevel.MANAGER;
                } else {
                    auth = AuthorizationLevel.ADMIN;
                }

                User u = new User(txtUserName.getText().toString(), txtPswrd.getText().toString(), auth);
                mainModel.addUser(u);
                Intent i = new Intent(register.this, Welcome.class);
                finish();
                startActivity(i);
            }



        }
    }

    /**
     * Returns to welcome screen
     */
    public void cancelOnClick(View v) {
        Intent i = new Intent(register.this, Welcome.class);
        finish();
        startActivity(i);
    }
}
