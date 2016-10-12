
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
	public String getwaterType() {
		return _waterType;
	}

	/**
	 * @return the _waterCond
	 */
	public String getwaterCond() {
		return _waterCond;
	}

	@Override
	public ObservableList<String> getAttributes() {
		ObservableList<String> attributes = FXCollections.observableArrayList();
		attributes.add("Report Number");
		attributes.add("Author");
		attributes.add("Location");
		attributes.add("Timestamp");
		attributes.add("Water Type");
		attributes.add("Water Condition");
		attributes.add("Description");
		return attributes;
	}

	@Override
	public ObservableList<String> getDetails() {
		ObservableList<String> details = FXCollections.observableArrayList();
		details.add(this.getNumber().get());
		details.add(this.getAuthor().get());
		details.add(this.getLocation().get());
		details.add(this.getTimestamp());
		details.add(this.getwaterType());
		details.add(this.getwaterCond());
		details.add(this.getDescription().get());
		return details;
	}
}