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

    /**Longitude for the report. */
    private final double _longitude;
    /**Latitude for the Report. */
    private final double _latitude;
    /**Location of the report. */
    private final StringProperty _location = new SimpleStringProperty();
    /**Description for the report. */
    private final StringProperty _description = new SimpleStringProperty();
    /**Author for the report. */
    private final StringProperty _author = new SimpleStringProperty();
    /**Report Number. */
    private final StringProperty _number = new SimpleStringProperty();
    /**Time Stamp for the report. */
    private final StringProperty _timestamp = new SimpleStringProperty();

    /**
     * The constructor for the report class.
     *
     * @param location location of the report
     * @param latitude latitude of the report
     * @param longitude longitude of the report
     * @param description description of the report
     * @param timestamp time stamp of the report
     * @param user user who made the report.
     */
    public Report(final String location, final double latitude,
                  final double longitude, final String description,
                  final String timestamp,
                  final String user) {
        _location.set(location);
        _latitude = latitude;
        _longitude = longitude;
        _description.set(description);
        _author.set(user);
        _number.set("-1");
        _timestamp.set(timestamp);
    }

    /**
     * The constructor for the report class.
     *
     * @param location location of the report
     * @param latitude latitude of the report
     * @param longitude longitude of the report
     * @param description description of the report
     * @param user user who made the report.
     */
    public Report(final String location, final double latitude,
                  final double longitude, final String description,
                  final String user) {
        _location.set(location);
        _latitude = latitude;
        _longitude = longitude;
        _description.set(description);
        _author.set(user);
        _number.set("-1");
        _timestamp.set(getTime());
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
        _location.set(location);
        _longitude = 0.0;
        _latitude = 0.0;
        _description.set(description);
        _author.set(user);
        _number.set("-1");
        _timestamp.set(timestamp);
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
        _location.set(location);
        _longitude = 0.0;
        _latitude = 0.0;
        _description.set(description);
        _author.set(user);
        _number.set("-1");
        _timestamp.set(getTime());
    }

    /**
     * report number getter.
     *
     * @return the report number
     */
    public final StringProperty getNumber() {
        return _number;
    }

    /**
     * report number setter.
     *
     * @param number the report number to be set
     */
    public final void setNumber(final int number) {
        _number.set("" + number);
    }

    /**
     * location getter.
     *
     * @return location of the water source
     */
    public final StringProperty getLocation() {
        return _location;
    }

    /**
     * description getter.
     *
     * @return description
     */
    public final StringProperty getDescription() {
        return _description;
    }

    /**
     * author getter.
     *
     * @return author
     */
    public final StringProperty getAuthor() {
        return _author;
    }

    /**
     * timestamp getter.
     *
     * @return formatted timestamp of the report
     */
    public final StringProperty getTimestamp() {
        return _timestamp;
    }

    /**
     * gets the observable list of the report.
     *
     * @return details of the report
     */
    public abstract ObservableList<String> getDetails();

    /**
     * gets the observable list of the attributes of the report.
     *
     * @return attributes of the report
     */
    public abstract ObservableList<String> getAttributes();

    /**
     * time getter.
     *
     * @return formatted timestamp of the now
     */
    public final String getTime() {
        GregorianCalendar c = new GregorianCalendar();
        return String.format("%d", c.get(Calendar.MONTH) + 1)
                + "/" + String.format("%d", c.get(Calendar.DATE)) + "/"
                + String.format("%d", c.get(Calendar.YEAR)) + " "
                + String.format("%d", c.get(Calendar.HOUR_OF_DAY))
                + ":" + String.format("%d", c.get(Calendar.MINUTE))
                + "." + String.format("%d", c.get(Calendar.SECOND));
    }

    /**
     * @return a string representation of the location.
     */
	@Override
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
