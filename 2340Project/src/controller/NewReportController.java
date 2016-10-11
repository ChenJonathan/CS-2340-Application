package controller;

import fxapp.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Report;
import model.UserReport;
import database.Model;
import database.UserDB;

public class NewReportController extends DialogController {

	@FXML
	private ComboBox<String> waterType;

	@FXML
	private ComboBox<String> waterCond;

	@FXML
	private TextArea description;

	@FXML
	private TextField theLocation;

	@FXML
	private void initialize() {
		waterType.getItems().addAll("Bottled", "Well", "Stream", "Lake", "Spring", "Other");
		waterCond.getItems().addAll("Portable", "Treatable-Muddy", "Treatable-Clear", "Waste");
	}

	@FXML
	public void handleCancel() {
		dialogStage.close();
	}

	@FXML
	public void handleSave() {
		try {
			String loc = theLocation.getText();
			String wt = waterType.getSelectionModel().getSelectedItem().toString();
			String wc = waterCond.getSelectionModel().getSelectedItem().toString();
			String des = description.getText();
			Report newReport = new UserReport(loc, des, UserDB.getCurrentUser(), wt, wc);
			Model.instance().addReport(newReport);
			dialogStage.close();
		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initOwner(Main.stage());
			alert.setTitle("Fields Incomplete");
			alert.setHeaderText("Fields Incomplete");
			alert.setContentText("One or more field is incomplete");
			alert.showAndWait();
		}
	}
}