package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class RegisterController {

	@FXML
	private TextField userField;
	
	@FXML
	private PasswordField passwordField;
	
	private Stage _dialogStage;
	
	private Main mainApplication;
	
	public void setDialogStage(Stage dialogStage) {
		_dialogStage = dialogStage;
	}
	
	public void setMainApp(Main main) {
		mainApplication = main;
	}
	
	@FXML
	public void handleOKPressed() {
		
		/* TODO insert user data into database */
		
	}
	
	@FXML
	public void handleCancelPressed() {
		_dialogStage.close();
	}
}
