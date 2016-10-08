
package model;

import java.util.Calendar;

/**
 * @author Wes
 *
 */
public class WorkerReport extends Report {

	private String _overallCond;
	private double _virusPPM;
	private double _contaminantPPM;

	/**
	 * @param location
	 * @param description
	 * @param timestamp
	 * @param user
	 */
	public WorkerReport(String location, String description, Calendar timestamp, User user) {
		super(location, description, timestamp, user);
		_overallCond = "N/A";
		_virusPPM = -1.0;
		_contaminantPPM = -1.0;
	}

	/**
	 * @param location
	 * @param waterType
	 * @param description
	 */
	public WorkerReport(String location, String waterType, String description) {
		super(location, description);
		_overallCond = "N/A";
		_virusPPM = -1.0;
		_contaminantPPM = -1.0;
	}

	/**
	 * @param location
	 * @param description
	 * @param timestamp
	 * @param user
	 * @param _overallCond
	 * @param _virusPPM
	 * @param _contaminantPPM
	 */
	public WorkerReport(String location, String description, Calendar timestamp, User user, String _overallCond,
			double _virusPPM, double _contaminantPPM) {
		super(location, description, timestamp, user);
		this._overallCond = _overallCond;
		this._virusPPM = _virusPPM;
		this._contaminantPPM = _contaminantPPM;
	}

	/**
	 * @return the _overallCond
	 */
	public String get_overallCond() {
		return _overallCond;
	}

	/**
	 * @return the _virusPPM
	 */
	public double get_virusPPM() {
		return _virusPPM;
	}

	/**
	 * @return the _contaminantPPM
	 */
	public double get_contaminantPPM() {
		return _contaminantPPM;
	}

}
