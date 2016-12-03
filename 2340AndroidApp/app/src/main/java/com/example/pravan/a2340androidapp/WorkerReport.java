package com.example.pravan.a2340androidapp;

/**
 * Created by Pravan on 12/3/2016.
 */

public class WorkerReport extends UserReport {
    /**The PPM of the water. */
    private double _virusPPM;
    /**Contamination level of the Water. */
    private double _contaminantPPM;

    /**
     * Constructor of the Worker Report.
     * @param location of the report
     * @param description of the report
     * @param timestamp of the report
     * @param user who made the report
     * @param virusPPM of the water
     * @param contaminantPPM of the water
     * @param longitude of the report
     * @param latitude of the report.
     * @param waterType of the water.
     * @param waterCond of the report
     */
    public WorkerReport(final String location, final double latitude,
                        final double longitude, final String description,
                        final String timestamp, final String user,
                        final String waterType, final String waterCond,
                        final double virusPPM, final double contaminantPPM) {
        super(location, latitude, longitude,
                description, timestamp, user,
                waterType, waterCond);
        this._virusPPM = virusPPM;
        this._contaminantPPM = contaminantPPM;
    }

    /**
     * Constructor of the Worker Report.
     * @param location of the report
     * @param description of the report
     * @param user who made the report
     * @param virusPPM of the water
     * @param contaminantPPM of the water
     * @param longitude of the report
     * @param latitude of the report.
     * @param waterType of the water.
     * @param waterCond of the report
     */
    public WorkerReport(final String location, final double latitude,
                        final double longitude, final String description,
                        final String user, final String waterType,
                        final String waterCond, final double virusPPM,
                        final double contaminantPPM) {
        super(location, latitude, longitude,
                description, user,
                waterType, waterCond);
        this._virusPPM = virusPPM;
        this._contaminantPPM = contaminantPPM;
    }

    /**
     * Constructor of the Worker Report.
     * @param location of the report
     * @param description of the report
     * @param timestamp of the report
     * @param user who made the report
     * @param virusPPM of the water
     * @param contaminantPPM of the water
     * @param waterType of the water.
     * @param waterCond of the report
     */
    public WorkerReport(final String location, final String description,
                        final String timestamp, final String user,
                        final String waterType, final String waterCond,
                        final double virusPPM, final double contaminantPPM) {
        super(location, description, timestamp, user, waterType, waterCond);
        this._virusPPM = virusPPM;
        this._contaminantPPM = contaminantPPM;
    }

    /**
     * Constructor of the Worker Report.
     * @param location of the report
     * @param description of the report
     * @param user who made the report
     * @param virusPPM of the water
     * @param contaminantPPM of the water
     * @param waterType of the water.
     * @param waterCond of the report
     */
    public WorkerReport(final String location, final String description,
                        final String user, final String waterType,
                        final String waterCond, final double virusPPM,
                        final double contaminantPPM) {
        super(location, description, user, waterType, waterCond);
        this._virusPPM = virusPPM;
        this._contaminantPPM = contaminantPPM;
    }

    /**
     * @return the _virusPPM
     */
    public final double getVirusPPM() {
        return _virusPPM;
    }

    /**
     * @return the _contaminantPPM
     */
    public final double getContaminantPPM() {
        return _contaminantPPM;
    }
}
