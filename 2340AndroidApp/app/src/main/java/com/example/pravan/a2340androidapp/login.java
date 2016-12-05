package com.example.pravan.a2340androidapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.w3c.dom.Text;

/**
 * Created by Pravan on 11/26/2016.
 */

public class login extends AppCompatActivity {

    private Model mainModel = Model.instance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void loginOnClick(View v) {
        EditText txtUserName = (EditText) findViewById(R.id.txtUserName);
        EditText txtPswrd = (EditText) findViewById(R.id.txtPswrd);

        if (txtUserName.getText().toString().matches("") || txtPswrd.getText().toString().matches("")) {
            //Create dialog box for this error
            new AlertDialog.Builder(login.this).setTitle("Login Error")
                    .setMessage("Please Enter a Username and Password")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
        } else {
            //check database for txtUserName.getText() && txtPswrd.getText()
            //If it doesnt exist
            if (!mainModel.authenticateUser(txtUserName.getText().toString(), txtPswrd.getText().toString())) {
                new AlertDialog.Builder(login.this).setTitle("Login Failure")
                        .setMessage("Invalid Username or Password")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
            } else {
                Intent i = new Intent(login.this, mainMap.class);
                finish();
                startActivity(i);
            }
        }
    }

    public void cancelOnClick(View v) {
        Intent i = new Intent(login.this, Welcome.class);
        finish();
        startActivity(i);
    }


}
