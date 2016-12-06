package com.example.pravan.a2340androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Pravan on 12/6/2016.
 */

public class ProfileController extends AppCompatActivity {

    EditText txtEmail;
    EditText txtAddress;
    EditText txtNumb;
    ListDB listDb = ListDB.getInstance();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtAddress = (EditText) findViewById(R.id.txtAddress);
        txtNumb = (EditText) findViewById(R.id.txtNumb);

        String email  = listDb.getCurrentUser().getEmail();
        String address = listDb.getCurrentUser().getAddress();
        String number = listDb.getCurrentUser().getPhoneNumber();

        if (!email.equals("")) {
            txtEmail.setText(email);
        } else {
            txtEmail.setText("N/A");
        }
        if (!address.equals("")) {
            txtAddress.setText(address);
        } else {
            txtAddress.setText("N/A");
        }
        if (!number.equals("")){
            txtNumb.setText(number);
        } else {
            txtNumb.setText("N/A");
        }
    }

    public void onSaveClick(View v) {
        listDb.getCurrentUser().setEmail(txtEmail.getText().toString());
        listDb.getCurrentUser().setAddress(txtAddress.getText().toString());
        listDb.getCurrentUser().setPhoneNumber(txtNumb.getText().toString());

        Intent i = new Intent(ProfileController.this, mainMap.class);
        finish();
        startActivity(i);
    }

    public void onCancelClick(View v) {
        Intent i = new Intent(ProfileController.this, mainMap.class);
        finish();
        startActivity(i);
    }




}
