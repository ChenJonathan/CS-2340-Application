package controller;

import database.Model;
import database.UserDB;
import fxapp.Main;
import javafx.fxml.FXML;
import model.User;

public class MainScreenController {
	
	private Main mainApplication;
	
	public void setMainApp(Main main) {
		mainApplication = main;
	}
	
	@FXML
	public void handleLogoutPressed() {
		mainApplication.showWelcomeScreen();
	}
	
	@FXML
	public void handleNewReportCreation() {
		mainApplication.showNewReportDialog();
	}
	
	@FXML
	public void handleViewReportPressed() {
		mainApplication.showViewReportDialog();
	}
	
	public void handleProfilePressed() {
		User user = UserDB.getCurrentUser();
		mainApplication.showProfilePage(user);

	}
}
