package controller;

import javafx.fxml.FXML;

public class WelcomeScreenController extends Controller {
	
	@FXML
	public void handleLoginPressed() {
		showDialog("../view/LoginDialog.fxml", "Login");
	}
	
	@FXML
	public void handleRegisterPressed() {
		showDialog("../view/RegisterDialog.fxml", "Register");
	}
}