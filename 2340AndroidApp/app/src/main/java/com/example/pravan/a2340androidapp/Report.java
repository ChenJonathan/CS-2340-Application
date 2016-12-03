package com.example.pravan.a2340androidapp;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Pravan on 12/3/2016.
 */

public abstract class Report {
    /**Longitude for the report. */
    private double _longitude = 0.0;
    /**Latitude for the Report. */
    private double _latitude = 0.0;
    /**Location of the report. */
    private String _location = "";
    /**Description for the report. */
    private String _description = "";
    /**Author fo the report. */
    private String _author = "";
    /**Report Number. */
    private String _number = "";
    /**Time Stamp for the report. */
    private String _timestamp = "";

    public Report(final String location, final double latitude,
                  final double longitude, final String description,
                  final String timestamp,
                  final String user) {
        _location = (location);
        _latitude = latitude;
        _longitude = longitude;
        _description = (description);
        _author = (user);
        _number = ("-1");
        _timestamp = (timestamp);
    }

    public Report(final String location, final double latitude,
                  final double longitude, final String description,
                  final String user) {
        _location = (location);
        _latitude = latitude;
        _longitude = longitude;
        _description = (description);
        _author = (user);
        _number = ("-1");
        _timestamp = (getTime());
    }

    /**
     * Make a new report with all required information.
     *
     * @param location
     *            the location covered by report
     * @param description
     *            TBD
     * @param user
     *            The user who created the report
     * @param timestamp time stamp of the report
     */
    public Report(final String location, final String description,
                  final String timestamp, final String user) {
        _location = (location);
        _longitude = 0.0;
        _latitude = 0.0;
        _description = (description);
        _author = (user);
        _number = ("-1");
        _timestamp = (timestamp);
    }

    /**
     * Make a new report with all required information.
     *
     * @param location the location covered by report
     * @param description TBD
     * @param user The user who created the report
     */
    public Report(final String location,
                  final String description, final String user) {
        _location = (location);
        _longitude = 0.0;
        _latitude = 0.0;
        _description = (description);
        _author = (user);
        _number = ("-1");
        _timestamp = (getTime());
    }

    public final String getTime() {
        GregorianCalendar c = new GregorianCalendar();
        return String.format("%d", c.get(Calendar.MONTH) + 1)
                + "/" + String.format("%d", c.get(Calendar.DATE)) + "/"
                + String.format("%d", c.get(Calendar.YEAR)) + " "
                + String.format("%d", c.get(Calendar.HOUR_OF_DAY))
                + ":" + String.format("%d", c.get(Calendar.MINUTE))
                + "." + String.format("%d", c.get(Calendar.SECOND));
    }

    public final String getNumber() {
        return _number;
    }

    /**
     * report number setter.
     *
     * @param number the report number to be set
     */
    public final void setNumber(final int number) {
        _number = ("" + number);
    }

    /**
     * location getter.
     *
     * @return location of the water source
     */
    public final String getLocation() {
        return _location;
    }

    /**
     * description getter.
     *
     * @return description
     */
    public final String getDescription() {
        return _description;
    }

    /**
     * author getter.
     *
     * @return author
     */
    public final String getAuthor() {
        return _author;
    }

    /**
     * timestamp getter.
     *
     * @return formatted timestamp of the report
     */
    public final String getTimestamp() {
        return _timestamp;
    }
    public final String toString() {
        return _location + " " + _author + " " + _timestamp + " ";
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Report)) {
            return false;
        }
        Report other = (Report) o;
        return this.toString().equals(other.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    /**
     * @return the latitude.
     */
    public final double getLatitude() {
        return _latitude;
    }

    /**
     * @return the longitude.
     */
    public final double getLongitude() {
        return _longitude;
    }

}
