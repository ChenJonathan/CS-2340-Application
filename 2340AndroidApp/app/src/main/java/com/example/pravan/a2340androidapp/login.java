package com.example.pravan.a2340androidapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.common.api.GoogleApiClient;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Pravan on 11/26/2016.
 */

public class login extends AppCompatActivity {

    //private Model mainModel = Model.instance();

    public Context context = login.this;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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
            //makes an array of string arguments for the AsyncTask
            String arr[] = {txtUserName.getText().toString(), txtPswrd.getText().toString()};
            LoginTask task = new LoginTask();
            //Starts the task with the array of arguments
            task.execute(arr);

        }
    }

    public void cancelOnClick(View v) {
        Intent i = new Intent(login.this, Welcome.class);
        finish();
        startActivity(i);
    }


    //Private inner class for the Async task
    class LoginTask extends AsyncTask<String, Void, User> {

        @Override
        protected User doInBackground(String... s) {
            //Does this upon startup
            //takes the paramaters and gets a response from the server
            User u = null;
            String name = s[0];
            String pass = s[1];
            HttpHandler sh = new HttpHandler();

            //Gets a string from the server related to the name and password
            String jsonString = sh.makeServiceCall("https://chenjonathan-cs-2340"
                    +"-api.herokuapp.com/login", "name=" + name + "&pass=" + pass);
            //checks if its null
            if (jsonString != null) {
                try {
                    //Make a new User object
                    JSONObject obj = new JSONObject(jsonString);
                    u = new User(obj.getString("name"), obj.getString("pass"), AuthorizationLevel.fromString(obj.getString("auth")));
                    u.setEmail(obj.getString("email"));
                    u.setAddress(obj.getString("address"));
                    u.setPhoneNumber(obj.getString("number"));

                } catch (Exception e) {
                    return null;
                }
            }
            return u;
        }



        protected void onPostExecute(User u) {
            if (u == null) {
                //If no user was created, or there was some failure, make a dialog box appear
                new AlertDialog.Builder(login.this).setTitle("Login Failure")
                        .setMessage("Invalid Username or Password")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
            } else {
                //else start the next activity with the User as an extra.
                Intent i = new Intent(login.this, mainMap.class);
                i.putExtra("User", u);
                finish();
                startActivity(i);
            }
        }

    }

}


