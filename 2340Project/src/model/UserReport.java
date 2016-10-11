
package model;

import java.util.Calendar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Wes 
 * The implementation for a user report
 *
 */
public class UserReport extends Report {

	private String _waterType;
	private String _waterCond;

	/**
	 * @param location
	 * @param description
	 * @param timestamp
	 * @param user
	 */
	public UserReport(String location, String description, Calendar timestamp, User user) {
		super(location, description, timestamp, user);
		_waterType = "N/A";
		_waterCond = "N/A";
	}

	/**
	 * @param location
	 * @param waterType
	 * @param description
	 */
	public UserReport(String location, String waterType, String description, User user) {
		super(location, description, user);
		_waterType = "N/A";
		_waterCond = "N/A";
	}

	/**
	 * @param location
	 * @param waterType
	 * @param description
	 */
	public UserReport(String location, String description, User user, String waterType, String waterCond) {
		super(location, description, user);
		_waterType = waterType;
		_waterCond = waterCond;
	}

	/**
	 * @return the _waterType
	 */
	public String get_waterType() {
		return _waterType;
	}

	/**
	 * @return the _waterCond
	 */
	public String get_waterCond() {
		return _waterCond;
	}

	@Override
	public ObservableList<String> getAttributes() {
		ObservableList<String> details = FXCollections.observableArrayList();
		details.add("Report Number");
		details.add("Author");
		details.add("Location");
		details.add("Timestamp");
		details.add("Water Type");
		details.add("Water Condition");
		return details;
	}

	@Override
	public ObservableList<String> getDetails() {
		ObservableList<String> attributes = FXCollections.observableArrayList();
		attributes.add(this.getNumber().get());
		attributes.add(this.getAuthor().get());
		attributes.add(this.getLocation().get());
		attributes.add(this.getTimestamp());
		attributes.add(this.get_waterType());
		attributes.add(this.get_waterCond());
		return attributes;
	}
}