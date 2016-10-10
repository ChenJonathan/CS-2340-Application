package model;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import database.UserDB;
import database.Model;
import database.ReportDB;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 * Created by Sathvik Kadaveru on 10/04/16.
 *
 * Represents a single water report
 *
 */
public abstract class Report {

	private final StringProperty _location = new SimpleStringProperty();
	private final StringProperty _description = new SimpleStringProperty();
	private final StringProperty _author = new SimpleStringProperty();
	private final StringProperty _number = new SimpleStringProperty();
	private Calendar _timestamp;

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
		_number.set("" + Model.getInstance().getReports().getIndex());
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
		_number.set("" + Model.getInstance().getReports().getIndex());
		_timestamp = new GregorianCalendar();
	}

	public StringProperty getNumber() {
		return _number;
	}

	public StringProperty getLocation() {
		return _location;
	}

	public StringProperty getDescription() {
		return _description;
	}

	public StringProperty getAuthor() {
		return _author;
	}

	public long getUnix() {
		return _timestamp.getTimeInMillis();
	}

	public abstract ObservableList<String> getDetails();

	public String getTimestamp() {
		return String.format("%d", _timestamp.get(Calendar.MONTH)) + "/"
				+ String.format("%d", _timestamp.get(Calendar.DATE) + 1) + "/"
				+ String.format("%d", _timestamp.get(Calendar.YEAR)) + " "
				+ String.format("%d", _timestamp.get(Calendar.HOUR_OF_DAY)) + ":"
				+ String.format("%d", _timestamp.get(Calendar.MINUTE)) + "."
				+ String.format("%d", _timestamp.get(Calendar.SECOND));
	}

	public String toString() {
		return _location + " " + _author + " " + getTimestamp() + " ";
	}

}
