package controller;

import fxapp.Main;
import model.Model;
import model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class Controller {
	
	private Main mainApplication;
	
	public void setMainApp(Main main) {
		mainApplication = main;
	}

	@FXML
	public void handleLoginPressed() {
		mainApplication.showLoginDialog();
	}
	
	@FXML
	public void handleRegisterPressed() {
		User newUser = new User();
		boolean okClicked = mainApplication.showRegisterDialog(newUser);
		if (okClicked) {
			if (!Model.getInstance().addUser(newUser)) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.initOwner(mainApplication.getMainScreen());
				alert.setTitle("User already exists");
				alert.setHeaderText("User already exists");
				alert.setContentText("A user with this username already exists");
				alert.showAndWait();
			}
		}
	}
}
