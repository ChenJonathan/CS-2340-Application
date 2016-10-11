package controller;

import database.Model;
import fxapp.Main;
import model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

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
