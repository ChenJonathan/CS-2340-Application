package database;

import java.util.LinkedList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Report;

public class ReportDB {

	private int index = 0;

	private ObservableList<Report> reports = FXCollections.observableList(new LinkedList<Report>());

	public boolean addReport(Report newReport) {
		index++;
		return reports.add(newReport);
	}
	
	public ObservableList<Report> getReports() {
		return reports;
	}

	public int getIndex() {
		return index;
	}

}
