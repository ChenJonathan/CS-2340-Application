package controller;

import fxapp.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.AuthorizationLevel;
import model.Report;
import model.ReportDB;

public class NewReportController {
	
	
	
	@FXML
	private ComboBox waterType;
	
	@FXML
	private ComboBox description;
	
	@FXML
	private TextField theLocation;

	private Stage _dialogStage;

	private Main mainApplication;
	
	/**
	 * Loads all
	 */
	@FXML
	private void initialize() {
		waterType.getItems().addAll("Bottled", "Well", "Stream", "Lake", "Spring", "Other");
		description.getItems().addAll("Potable", "Treatable-Muddy", "Treatable-Clear", "Waste");
	}

	public void setDialogStage(Stage dialogStage) {
		_dialogStage = dialogStage;
		
	}

	public void setMainApp(Main main) {
		mainApplication = main;
	}
	
	@FXML
	public void handleCancel() {
		_dialogStage.close();
	}
	
	@FXML
	public void handleSave() {
		try {
			String loc = theLocation.getText();
			String wt = waterType.getSelectionModel().getSelectedItem().toString();
			String des = description.getSelectionModel().getSelectedItem().toString();
			Report newReport = new Report(loc, wt, des);
			ReportDB.addReport(newReport);
			_dialogStage.close();
		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initOwner(mainApplication.getMainScreen());
			alert.setTitle("Fields Incomplete");
			alert.setHeaderText("Fields Incomplete");
			alert.setContentText("One or more field is incomplete");
			alert.showAndWait();
		}
	}

}
