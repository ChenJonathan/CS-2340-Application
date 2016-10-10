package controller;

import database.Model;
import database.UserDB;
import fxapp.Main;
import model.User;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

/**
 * Controller for the Login Dialog.
 * @author Alok Tripathy
 *
 */
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
		
		UserDB database = Model.getInstance().getUsers();
		if (database.userExists(user, passwd)) {
			_dialogStage.close();
			mainApplication.showMainScreen();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initOwner(mainApplication.getMainScreen());
			alert.setTitle("User Not Found");
			alert.setHeaderText("User Not Found");
			alert.setContentText("Username or password incorrect");
			alert.showAndWait();
		}
	}
	
	@FXML
	public void handleCancelPressed() {
		_dialogStage.close();
	}
}
