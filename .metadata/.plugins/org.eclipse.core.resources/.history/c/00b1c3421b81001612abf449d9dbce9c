package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class LoginController {

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
		String user = userField.getText();
		String passwd = passwordField.getText();
		
		if (user.equals("JWASP") && passwd.equals("/etc/shadow")) {
			_dialogStage.close();
			mainApplication.showMainScreen();
		} else {
			userField.setText("Invalid login");
		}
		
		
	}
}
