package controller;

import fxapp.Main;
import model.User;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import model.AuthorizationLevel;

/**
 * 
 * Controller for the Register Dialog.
 * @author Alok Tripathy
 *
 */
public class RegisterController {

	@FXML
	private TextField userField;
	
	@FXML
	private PasswordField passwordField;
	
	@FXML
	private ComboBox authBox;
	
	private Stage _dialogStage;
	
	private Main mainApplication;
	
	private User _user;
	
	private boolean _okClicked;
	
	public void setDialogStage(Stage dialogStage) {
		_dialogStage = dialogStage;
	}
	
	public void setMainApp(Main main) {
		mainApplication = main;
	}
	
	/**
	 * Loads all authorization levels into auth ComboBox on launch.
	 */
	@FXML
	private void initialize() {
		for (AuthorizationLevel level : AuthorizationLevel.values()) {
			authBox.getItems().add(level.getName());
		}
	}
	
	/**
	 * Handler for when the OK button on the register dialog is clicked.
	 */
	@FXML
	public void handleOKPressed() {
		_user.setName(userField.getText());
		_user.setPassword(passwordField.getText());
		String auth = authBox.getSelectionModel().getSelectedItem().toString();
		for (AuthorizationLevel authLevel : AuthorizationLevel.values()) {
			if (auth.equals(authLevel.getName())) {
				_user.setAuth(authLevel);
				break;
			}
		}
		_okClicked = true;
		_dialogStage.close();
	}
	
	/**
	 * Getter for whether the OK button has been clicked.
	 * @return
	 */
	public boolean isOkClicked() {
		return _okClicked;
	}
	
	/**
	 * Sets the user to be edited in the dialog.
	 * @param user the user who will be edited.
	 */
	public void setUser(User user) {
		_user = user;
	}
	
	@FXML
	public void handleCancelPressed() {
		_dialogStage.close();
	}
}
