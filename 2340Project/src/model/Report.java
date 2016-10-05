package model;

import javafx.beans.property.StringProperty;

/**
 * Created by Sathvik Kadaveru on 10/04/16.
 *
 * Represents a single water report
 *
 */
public class Report {
	
	private StringProperty _location;
	private StringProperty _description;
	private String _author;
	
	/**
     * Make a new report
     * @param location      the location covered by report
     * @param description	TBD
     * @param user			The user who created the report
     */
	public Report(StringProperty location, StringProperty description, User user) {
		_location = location;
		_description = description;
		_author = user.getName();
	}
	
	
	public String getLocation() {
		return _location.get();
	}
	
	public String getDescription() {
		return _description.get();
	}
	
	public String getAuthor() {
		return _author;
	}

}
