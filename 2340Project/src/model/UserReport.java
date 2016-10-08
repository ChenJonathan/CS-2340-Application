/**
 * 
 */
package model;

import java.util.Calendar;

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
	public UserReport(String location, String waterType, String description) {
		super(location, description);
		_waterType = "N/A";
		_waterCond = "N/A";
	}

	/**
	 * @param location
	 * @param waterType
	 * @param description
	 */
	public UserReport(String location, String description, String waterType, String waterCond) {
		super(location, description);
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

}
