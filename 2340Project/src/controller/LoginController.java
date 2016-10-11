package controller;

import database.Model;
import fxapp.Main;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;

/**
 * Controller for the Login Dialog.
 * @author Alok Tripathy
 */
public class LoginController extends DialogController {

	@FXML
	private TextField userField;
	
	@FXML
	private PasswordField passwordField;
	
	@FXML
	public void handleOKPressed() {
		String user = userField.getText();
		String pass = passwordField.getText();
		
		if (Model.instance().authenticateUser(user, pass)) {
			dialogStage.close();
			showScreen("../view/MainScreen.fxml", "Main screen");
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initOwner(Main.stage());
			alert.setTitle("User Not Found");
			alert.setHeaderText("User Not Found");
			alert.setContentText("Username or password incorrect");
			alert.showAndWait();
		}
	}
	
	@FXML
	public void handleCancelPressed() {
		dialogStage.close();
	}
}