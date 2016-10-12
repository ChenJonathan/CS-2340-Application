package model;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 * Created by Sathvik Kadaveru on 10/04/16. Represents a single water report
 */
/**
 * @author Wes
 *
 */
public abstract class Report {

    protected final StringProperty _location = new SimpleStringProperty();
    protected final StringProperty _description = new SimpleStringProperty();
    protected final StringProperty _author = new SimpleStringProperty();
    protected final StringProperty _number = new SimpleStringProperty();
    protected Calendar _timestamp;

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
    public Report(String location, String description, Calendar timestamp, User user) {
        _location.set(location);
        _description.set(description);
        _author.set(user.getName());
        _number.set("-1");
        _timestamp = timestamp;
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
    public Report(String location, String description, User user) {
        _location.set(location);
        _description.set(description);
        _author.set(user.getName());
        _number.set("-1");
        _timestamp = new GregorianCalendar();
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
     * unix timestamp getter
     * 
     * @return time stamp
     */
    public long getUnix() {
        return _timestamp.getTimeInMillis();
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
     * timestamp getter
     * 
     * @return formatted timestamp of the report
     */
    public String getTimestamp() {
        return String.format("%d", _timestamp.get(Calendar.MONTH) + 1) + "/" + String.format("%d", _timestamp.get(Calendar.DATE)) + "/" + String.format("%d", _timestamp.get(Calendar.YEAR)) + " " + String.format("%d", _timestamp.get(Calendar.HOUR_OF_DAY)) + ":" + String.format("%d", _timestamp.get(Calendar.MINUTE)) + "." + String.format("%d", _timestamp.get(Calendar.SECOND));
    }

    public String toString() {
        return _location + " " + _author + " " + getTimestamp() + " ";
    }
}