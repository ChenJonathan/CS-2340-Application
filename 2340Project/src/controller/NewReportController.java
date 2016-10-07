package controller;

import database.ReportDB;
import fxapp.Main;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.*;

public class NewReportController {
	
	@FXML
	private ChoiceBox waterType;
	
	@FXML
	private ChoiceBox waterCond;
	
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
		String wt = "<TODO>";
		String des = description.getText();
		UserReport newReport = new UserReport(loc, wt, des);
		ReportDB.addReport(newReport);
		_dialogStage.close();
	}

}
