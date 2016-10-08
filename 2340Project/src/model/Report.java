package model;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import database.UserDB;
import javafx.beans.property.StringProperty;

/**
 * Created by Sathvik Kadaveru on 10/04/16.
 *
 * Represents a single water report
 *
 */
public abstract class Report {

	private String _location;
	private String _description;
	private String _author;
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
		_location = location;
		_description = description;
		_author = user.getName();
		_timestamp = timestamp;
	}

	public Report(String location, String description) {
		_location = location;
		_description = description;
		_author = UserDB.getCurrentUser().getName();
		_timestamp = new GregorianCalendar();
	}

	public String getLocation() {
		return _location;
	}

	public String getDescription() {
		return _description;
	}

	public String getAuthor() {
		return _author;
	}

	public long getUnix() {
		return _timestamp.getTimeInMillis();
	}

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
