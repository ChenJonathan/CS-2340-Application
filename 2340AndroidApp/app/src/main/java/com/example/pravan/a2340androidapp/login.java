package com.example.pravan.a2340androidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import org.w3c.dom.Text;

/**
 * Created by Pravan on 11/26/2016.
 */

public class login extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void loginOnClick() {
        EditText txtUserName = (EditText) findViewById(R.id.txtUserName);
        EditText txtPswrd = (EditText) findViewById(R.id.txtPswrd);

        if (txtUserName.getText().equals("") || txtPswrd.getText().equals("")) {
            //Create dialog box for this error
        } else {
            //check database for txtUserName.getText() && txtPswrd.getText()

            //If it works
            Intent i = new Intent(login.this, mainMap.class);
            //If we want to pass the current user in we would do
            //i.putExtra("User", User u);
            //We would need user to extedn the Serializable interface
            finish();
            startActivity(i);
        }
    }

    public void cancelOnClick() {
        Intent i = new Intent(login.this, Welcome.class);
        finish();
        startActivity(i);
    }


}
