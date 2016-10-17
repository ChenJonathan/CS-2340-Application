
package model;

import java.util.Calendar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Wesley The implementation of a report for a worker
 */
public class WorkerReport extends UserReport {

	private double _virusPPM;
	private double _contaminantPPM;

	/**
	 * @param location
	 * @param description
	 * @param timestamp
	 * @param user
	 */
	public WorkerReport(String location, String description, Calendar timestamp, String user) {
		super(location, description, timestamp, user);
		_virusPPM = -1.0;
		_contaminantPPM = -1.0;
	}

	/**
	 * @param location
	 * @param description
	 */
	public WorkerReport(String location, String description, String user) {
		super(location, description, user);
		_virusPPM = -1.0;
		_contaminantPPM = -1.0;
	}

	/**
	 * @param location
	 * @param description
	 * @param timestamp
	 * @param user
	 * @param _virusPPM
	 * @param _contaminantPPM
	 */
	public WorkerReport(String location, String description, Calendar timestamp, String user, 
	        String waterType, String waterCond, double _virusPPM, double _contaminantPPM) {
		super(location, description, timestamp, user, waterType, waterCond);
		this._virusPPM = _virusPPM;
		this._contaminantPPM = _contaminantPPM;
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
        attributes.add("Virus PPM");
		attributes.add("Contaminant PPM");
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
        details.add("" + this.getVirusPPM());
		details.add("" + this.getContaminantPPM());
		details.add(this.getDescription().get());
		return details;
	}
}