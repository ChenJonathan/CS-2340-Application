
package model;

import java.util.Calendar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Wes The implementation for a user report
 *
 */
public class UserReport extends Report {

    private String _waterType;
    private String _waterCond;

    /**
     * 
     * no latitude/longitute constructor
     * 
     * @param location
     * @param description
     */
    public UserReport(String location, double latitude, double longitude, String description, String timestamp,
            String user, String waterType, String waterCond) {
        super(location, latitude, longitude, description, timestamp, user);
        _waterType = waterType;
        _waterCond = waterCond;
    }

    /**
     * no timestamp constructor
     * 
     * @param location
     * @param description
     */
    public UserReport(String location, double latitude, double longitude, String description, String user,
            String waterType, String waterCond) {
        super(location, latitude, longitude, description, user);
        _waterType = waterType;
        _waterCond = waterCond;
    }

    /**
     * 
     * no latitude/longitute constructor
     * 
     * @param location
     * @param description
     */
    public UserReport(String location, String description, String timestamp, String user, String waterType,
            String waterCond) {
        super(location, description, timestamp, user);
        _waterType = waterType;
        _waterCond = waterCond;
    }

    /**
     * no latitude/longitute or timestamp constructor
     * 
     * @param location
     * @param description
     */
    public UserReport(String location, String description, String user, String waterType, String waterCond) {
        super(location, description, user);
        _waterType = waterType;
        _waterCond = waterCond;
    }

    /**
     * @return the _waterType
     */
    public String getWaterType() {
        return _waterType;
    }

    /**
     * @return the _waterCond
     */
    public String getWaterCond() {
        return _waterCond;
    }

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