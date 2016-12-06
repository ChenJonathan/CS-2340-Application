package com.example.pravan.a2340androidapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import org.json.JSONObject;


/**
 * Created by Pravan on 11/26/2016.
 */

public class register extends AppCompatActivity {

    //private Model mainModel = Model.instance();
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
        String auth = "";
        if (checkedRdoBtn == 0){
            auth = "USER";
        } else if (checkedRdoBtn == 1) {
            auth = "WORKER";
        } else if (checkedRdoBtn == 2) {
            auth = "MANAGER";
        } else {
            auth = "ADMIN";
        }
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
            RegisterTask task = new RegisterTask();
            String arr[] = {txtUserName.getText().toString(), txtPswrd.getText().toString(), auth};
            task.execute(arr);
            //check availability and add to database
//            if (mainModel.checkUserExists(txtUserName.getText().toString())) {
//                new AlertDialog.Builder(register.this).setTitle("Register Error")
//                        .setMessage("Username is taken")
//                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        }).show();
//            } else {

                //mainModel.addUser(u);

            //}



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


    //Inner class for the register task
    class RegisterTask extends AsyncTask<String, Void, User> {


        protected User doInBackground(String ... s) {
            //String array values
            String name = s[0];
            String pass = s[1];
            String auth = s[2];
            HttpHandler sh = new HttpHandler();
            User u = null;
            //Get a response from the server
            String jString = sh.makeServiceCallWithHeader("https://chenjonathan-cs-2340"
                    +"-api.herokuapp.com/login" + name);

            //Try getting a Json object from that string, if it returns
            //null make a new user to add to the database
            try {
                //If object is null, then it doesnt exist in the database
                //Unfortunately it always returns null so this doesnt work
                JSONObject obj = new JSONObject(jString);

            } catch (Exception e) {
                AuthorizationLevel lvl = AuthorizationLevel.USER;
                if (auth.equals("WORKER")) {
                    lvl = AuthorizationLevel.WORKER;
                } else if (auth.equals("MANAGER")) {
                    lvl = AuthorizationLevel.MANAGER;
                } else if (auth.equals("ADMIN")) {
                    lvl = AuthorizationLevel.ADMIN;
                }
                u = new User(name, pass, lvl);
                HttpHandler reg = new HttpHandler();
                reg.put("https://chenjonathan-cs"
                        + "-2340-api.herokuapp.com/register", "name=" + u.getName()
                        + "&pass=" + u.getPassword() + "&auth=" + u.getAuth()
                        + "&email=" + u.getEmail() + "&phone=" + u.getPhoneNumber()
                        + "&address=" + u.getAddress());
                //System.out.println("The Http Call or something: " + u.getName());
                return u;
            }
            return u;
        }

        protected void onPostExecute (User u) {
            //If the user is null, it already existed


            if (u == null) {
                new AlertDialog.Builder(register.this).setTitle("Register Error")
                        .setMessage("Username is taken")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
            } else {
                //Start the welcome activity again.
                Intent i = new Intent(register.this, Welcome.class);
                finish();
                startActivity(i);
            }
        }
    }
}
