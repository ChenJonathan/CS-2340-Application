
package model;

import java.util.Calendar;

import javafx.collections.ObservableList;

/**
 * @author Wes
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
	public ObservableList<String> getDetails() {
		// TODO Auto-generated method stub
		return null;
	}

}
