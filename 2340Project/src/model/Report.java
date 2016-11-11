package model;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 * Created by Sathvik Kadaveru on 10/04/16. Represents a single water report
 */

public abstract class Report {

    protected final double _longitude;
    protected final double _latitude;
    protected final StringProperty _location = new SimpleStringProperty();
    protected final StringProperty _description = new SimpleStringProperty();
    protected final StringProperty _author = new SimpleStringProperty();
    protected final StringProperty _number = new SimpleStringProperty();
    protected final StringProperty _timestamp = new SimpleStringProperty();

    /**
     * Make a new report with all required information
     * 
     * @param location
     *            the location covered by report
     * @param description
     *            TBD
     * @param user
     *            The user who created the report
     */
    public Report(String location, double latitude, double longitude, String description, String timestamp,
            String user) {
        _location.set(location);
        _latitude = latitude;
        _longitude = longitude;
        _description.set(description);
        _author.set(user);
        _number.set("-1");
        _timestamp.set(timestamp);
    }

    /**
     * Make a new report with all required information
     * 
     * @param location
     *            the location covered by report
     * @param description
     *            TBD
     * @param user
     *            The user who created the report
     */
    public Report(String location, double latitude, double longitude, String description, String user) {
        _location.set(location);
        _latitude = latitude;
        _longitude = longitude;
        _description.set(description);
        _author.set(user);
        _number.set("-1");
        _timestamp.set(getTime());
    }

    /**
     * Make a new report with all required information
     * 
     * @param location
     *            the location covered by report
     * @param description
     *            TBD
     * @param user
     *            The user who created the report
     */
    public Report(String location, String description, String timestamp, String user) {
        _location.set(location);
        _longitude = 0.0;
        _latitude = 0.0;
        _description.set(description);
        _author.set(user);
        _number.set("-1");
        _timestamp.set(timestamp);
    }

    /**
     * Make a new report with all required information
     * 
     * @param location the location covered by report
     * @param description TBD
     * @param user The user who created the report
     */
    public Report(String location, String description, String user) {
        _location.set(location);
        _longitude = 0.0;
        _latitude = 0.0;
        _description.set(description);
        _author.set(user);
        _number.set("-1");
        _timestamp.set(getTime());
    }

    /**
     * report number getter
     * 
     * @return the report number
     */
    public StringProperty getNumber() {
        return _number;
    }

    /**
     * report number setter
     * 
     * @param number the report number to be set
     */
    public void setNumber(int number) {
        _number.set("" + number);
    }

    /**
     * location getter
     * 
     * @return location of the water source
     */
    public StringProperty getLocation() {
        return _location;
    }

    /**
     * description getter
     * 
     * @return description
     */
    public StringProperty getDescription() {
        return _description;
    }

    /**
     * author getter 
     * 
     * @return author
     */
    public StringProperty getAuthor() {
        return _author;
    }

    /**
     * timestamp getter
     * 
     * @return formatted timestamp of the report
     */
    public StringProperty getTimestamp() {
        return _timestamp;
    }

    /**
     * gets the observable list of the report
     * 
     * @return details of the report
     */
    public abstract ObservableList<String> getDetails();

    /**
     * gets the observable list of the attributes of the report
     * 
     * @return attributes of the report
     */
    public abstract ObservableList<String> getAttributes();

    /**
     * time getter
     * 
     * @return formatted timestamp of the now
     */
    public String getTime() {
        GregorianCalendar c = new GregorianCalendar();
        return String.format("%d", c.get(Calendar.MONTH) + 1) + "/" + String.format("%d", c.get(Calendar.DATE)) + "/"
                + String.format("%d", c.get(Calendar.YEAR)) + " " + String.format("%d", c.get(Calendar.HOUR_OF_DAY))
                + ":" + String.format("%d", c.get(Calendar.MINUTE)) + "." + String.format("%d", c.get(Calendar.SECOND));
    }

    @Override
    public String toString() {
        return "[" + _location.get() + " " + _description.get() + " " + _author.get() + " " + _timestamp.get() + " ]";
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

    public double getLatitude() {
        return _latitude;
    }

    public double getLongitude() {
        return _longitude;
    }
}