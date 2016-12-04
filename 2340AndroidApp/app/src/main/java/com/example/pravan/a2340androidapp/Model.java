package com.example.pravan.a2340androidapp;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Pravan on 12/4/2016.
 */

public class Model extends AsyncTask<Object, Void, JSONObject> {

    private static final Model mainModel = new Model();
    URL request;
    HttpURLConnection connection;

    public static Model instance() {
        return mainModel;
    }

    protected JSONObject doInBackground(Object ... params) {
        JSONObject jsonresp = null;
        try {
            request = new URL("https://chenjonathan-cs-2340-api.herokuapp.com/user/");
            connection = (HttpURLConnection) request.openConnection();
            connection.setRequestProperty("Accept-Encoding", "identity");
            connection.connect();


        } catch (Exception e) {

        }
        return  jsonresp;
    }

}