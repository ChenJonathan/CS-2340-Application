
package model;

import javafx.collections.ObservableList;

/**
 * @author Wesley The implementation of a report for a worker
 */
public class WorkerReport extends UserReport {

    private double _virusPPM;
    private double _contaminantPPM;

    /**
     * @param location
     * @param description
     * @param timestamp
     * @param user
     * @param _virusPPM
     * @param _contaminantPPM
     */
    public WorkerReport(String location, double latitude, double longitude, String description, String timestamp,
            String user, String waterType, String waterCond, double _virusPPM, double _contaminantPPM) {
        super(location, latitude, longitude, description, timestamp, user, waterType, waterCond);
        this._virusPPM = _virusPPM;
        this._contaminantPPM = _contaminantPPM;
    }

    /**
     * @param location
     * @param description
     * @param timestamp
     * @param user
     * @param _virusPPM
     * @param _contaminantPPM
     */
    public WorkerReport(String location, double latitude, double longitude, String description, String user,
            String waterType, String waterCond, double _virusPPM, double _contaminantPPM) {
        super(location, latitude, longitude, description, user, waterType, waterCond);
        this._virusPPM = _virusPPM;
        this._contaminantPPM = _contaminantPPM;
    }

    /**
     * @param location
     * @param description
     * @param timestamp
     * @param user
     * @param _virusPPM
     * @param _contaminantPPM
     */
    public WorkerReport(String location, String description, String timestamp, String user, String waterType,
            String waterCond, double _virusPPM, double _contaminantPPM) {
        super(location, description, timestamp, user, waterType, waterCond);
        this._virusPPM = _virusPPM;
        this._contaminantPPM = _contaminantPPM;
    }

    /**
     * @param location
     * @param description
     * @param timestamp
     * @param user
     * @param _virusPPM
     * @param _contaminantPPM
     */
    public WorkerReport(String location, String description, String user, String waterType, String waterCond,
            double _virusPPM, double _contaminantPPM) {
        super(location, description, user, waterType, waterCond);
        this._virusPPM = _virusPPM;
        this._contaminantPPM = _contaminantPPM;
    }

    /**
     * @return the _virusPPM
     */
    public double getVirusPPM() {
        return _virusPPM;
    }

    /**
     * @return the _contaminantPPM
     */
    public double getContaminantPPM() {
        return _contaminantPPM;
    }

    @Override
    public ObservableList<String> getAttributes() {
        ObservableList<String> attributes = super.getAttributes();
        attributes.remove(attributes.size() - 1);
        attributes.add("Virus PPM");
        attributes.add("Contaminant PPM");
        attributes.add("Description");
        return attributes;
    }

    @Override
    public ObservableList<String> getDetails() {
        ObservableList<String> details = super.getDetails();
        details.remove(details.size() - 1);
        details.add("" + this.getVirusPPM());
        details.add("" + this.getContaminantPPM());
        details.add(this.getDescription().get());
        return details;
    }
}