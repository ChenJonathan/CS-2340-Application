
package model;

import javafx.collections.ObservableList;

/**
 * @author Wesley The implementation of a report for a worker.
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

    @Override
    public final ObservableList<String> getAttributes() {
        ObservableList<String> attributes = super.getAttributes();
        attributes.remove(attributes.size() - 1);
        attributes.add("Virus PPM");
        attributes.add("Contaminant PPM");
        attributes.add("Description");
        return attributes;
    }

    @Override
    public final ObservableList<String> getDetails() {
        ObservableList<String> details = super.getDetails();
        details.remove(details.size() - 1);
        details.add("" + this.getVirusPPM());
        details.add("" + this.getContaminantPPM());
        details.add(this.getDescription().get());
        return details;
    }
}
