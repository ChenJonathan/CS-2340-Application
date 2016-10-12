
package model;

import java.util.Calendar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Wesley The implementation of a report for a worker
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
	public WorkerReport(String location, String waterType, String description, User user) {
		super(location, description, user);
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
	public String getOverallCond() {
		return _overallCond;
	}

	/**
	 * @return the _virusPPM
	 */
	public double getVirusPPM() {
		return _virusPPM;
	}

	/**
	 * @return the _contaminantPPM
	 */
	public double getContaminantPPM() {
		return _contaminantPPM;
	}

	@Override
	public ObservableList<String> getAttributes() {
		ObservableList<String> attributes = FXCollections.observableArrayList();
		attributes.add("Report Number");
		attributes.add("Author");
		attributes.add("Location");
		attributes.add("Timestamp");
		attributes.add("Overall Condition");
		attributes.add("Contaminant PPM");
		attributes.add("Virus PPM");
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
		details.add(this.getOverallCond());
		details.add("" + this.getContaminantPPM());
		details.add("" + this.getVirusPPM());
		details.add(this.getDescription().get());
		return details;
	}
}