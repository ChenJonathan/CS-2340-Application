package controller;

import fxapp.Main;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Report;
import model.ReportDB;

public class NewReportController {
	
	
	
	@FXML
	private TextField waterType;
	
	@FXML
	private TextArea description;
	
	@FXML
	private TextField theLocation;

	private Stage _dialogStage;

	private Main mainApplication;

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
		String loc = theLocation.getText();
		String wt = waterType.getText();
		String des = description.getText();
		Report newReport = new Report(loc, wt, des);
		ReportDB.addReport(newReport);
	}

}
