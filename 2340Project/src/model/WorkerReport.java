
package model;

import java.util.Calendar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Wesley
 *	The implementation of a report for a worker
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

	@Override
	public ObservableList<String> getAttributes() {
		ObservableList<String> details = FXCollections.observableArrayList();
		details.add("Report Number");
		details.add("Author");
		details.add("Location");
		details.add("Timestamp");
		details.add("Overall Condition");
		details.add("Contaminant PPM");
		details.add("Virus PPM");
		return details;
	}

	@Override
	public ObservableList<String> getDetails() {
		ObservableList<String> attributes = FXCollections.observableArrayList();
		attributes.add(this.getNumber().get());
		attributes.add(this.getAuthor().get());
		attributes.add(this.getLocation().get());
		attributes.add(this.getTimestamp());
		attributes.add(this.get_overallCond());
		attributes.add("" + this.get_contaminantPPM());
		attributes.add("" + this.get_virusPPM());
		return attributes;
	}
}