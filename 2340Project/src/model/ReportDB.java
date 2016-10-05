package model;

import java.util.LinkedList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ReportDB {
	
	public static ObservableList<Report> reports = FXCollections.observableList(new LinkedList<Report>());
	
	public static void addReport(Report newReport) {
		reports.add(newReport);
	}

}
