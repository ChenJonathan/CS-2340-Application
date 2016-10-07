package controller;

import database.ReportDB;
import fxapp.Main;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Report;

public class ViewReportController {
	
	
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
	public void handleClose() {
		_dialogStage.close();
	}
	
	@FXML
	public void handleNewReport() {
		mainApplication.showNewReportDialog();
	}


}
