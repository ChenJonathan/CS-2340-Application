
package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Wes The implementation for a user report.
 *
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

    /**
     * @return an observable list of all the reports.
     */
    @Override
    public ObservableList<String> getAttributes() {
        ObservableList<String> attributes = FXCollections.observableArrayList();
        attributes.add("Author");
        attributes.add("Timestamp");
        attributes.add("Location");
        attributes.add("Water Type");
        attributes.add("Water Condition");
        attributes.add("Description");
        return attributes;
    }

    /**
     * @return an Observable List of all the report details
     */
    @Override
    public ObservableList<String> getDetails() {
        ObservableList<String> details = FXCollections.observableArrayList();
        details.add(this.getAuthor().get());
        details.add(this.getTimestamp().get());
        details.add(this.getLocation().get());
        details.add(this.getWaterType());
        details.add(this.getWaterCond());
        details.add(this.getDescription().get());
        return details;
    }
}
