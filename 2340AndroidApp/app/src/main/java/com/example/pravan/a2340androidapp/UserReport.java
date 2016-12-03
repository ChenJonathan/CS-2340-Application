package com.example.pravan.a2340androidapp;

/**
 * Created by Pravan on 12/3/2016.
 */

public class UserReport extends Report {
    /**Water type for the report */
    private String _waterType;
    /**Water condition for the report. */
    private String _waterCond;

    /**
     * Constructor for a report.
     * @param location of the report
     * @param latitude of the report
     * @param longitude of the report
     * @param description of the report
     * @param timestamp of the report
     * @param user who made the report
     * @param waterType of the report
     * @param waterCond of the report
     */
    public UserReport(final String location, final double latitude,
                      final double longitude, final String description,
                      final String timestamp, final String user,
                      final String waterType, final String waterCond) {
        super(location, latitude, longitude, description, timestamp, user);
        _waterType = waterType;
        _waterCond = waterCond;
    }

    /**
     * Another constructor.
     * @param location of the report
     * @param latitude of the report
     * @param longitude of the report
     * @param description of the report
     * @param user who made the report
     * @param waterType of the report
     * @param waterCond of the report
     */
    public UserReport(final String location, final double latitude,
                      final double longitude, final String description,
                      final String user, final String waterType,
                      final String waterCond) {
        super(location, latitude, longitude, description, user);
        _waterType = waterType;
        _waterCond = waterCond;
    }

    /**
     * No latitude or latitude constrctor.
     * @param location of the report
     * @param description of the report
     * @param timestamp of the report
     * @param user who made the report
     * @param waterType of the report
     * @param waterCond of the report
     */
    public UserReport(final String location, final String description,
                      final String timestamp, final String user,
                      final String waterType, final String waterCond) {
        super(location, description, timestamp, user);
        _waterType = waterType;
        _waterCond = waterCond;
    }

    /**
     * No timestamp constructor.
     * @param location of the report
     * @param description of the report
     * @param user who made the report
     * @param waterType of the report
     * @param waterCond of the report
     */
    public UserReport(final String location, final String description,
                      final String user, final String waterType,
                      final String waterCond) {
        super(location, description, user);
        _waterType = waterType;
        _waterCond = waterCond;
    }

    /**
     * @return the _waterType
     */
    public final String getWaterType() {
        return _waterType;
    }

    /**
     * @return the _waterCond
     */
    public final String getWaterCond() {
        return _waterCond;
    }

}
