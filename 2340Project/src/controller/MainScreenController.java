package controller;

import fxapp.Main;
import javafx.fxml.FXML;

public class MainScreenController {
	
	private Main mainApplication;
	
	public void setMainApp(Main main) {
		mainApplication = main;
	}
	
	@FXML
	public void handleLogoutPressed() {
		mainApplication.showWelcomeScreen();
	}
}
