package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Wesley on 10/17/16. Adapted from robertwaters's location class
 * Just a class to hold some data we might want to display on the map
 */
public class Location {

    private final double longitude;
    private final double latitude;
    private final String title;

    public Location(double lat, double lg, String ti) {
        longitude = lg;
        latitude = lat;
        title = ti;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getTitle() {
        return title;
    }

    public static Location makeFromString(String str) {

        String[] tokens = str.split("\t");
        double longit = 0;
        double lat = 0;
        try {
            longit = Double.parseDouble(tokens[0]);
            lat = Double.parseDouble(tokens[1]);
        } catch (Exception e) {
            return null;
        }
        return new Location(lat, longit, tokens[2]);

    }
}
