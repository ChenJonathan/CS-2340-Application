package controller;

import database.UserDB;
import javafx.fxml.FXML;
import model.User;

public class MainScreenController extends Controller {
	
	@FXML
	public void handleLogoutPressed() {
		showScreen("../view/WelcomeScreen.fxml", "Welcome!");
	}
	
	@FXML
	public void handleNewReportCreation() {
        showDialog("../view/NewReport.fxml", "New Report");
	}
	
	@FXML
	public void handleViewReportPressed() {
        showDialog("../view/ViewReport.fxml", "View Reports");
	}
	
	public void handleProfilePressed() {
        User user = UserDB.getCurrentUser();
        ProfileController controller = (ProfileController)showDialog("../view/ProfilePage.fxml", user.getName() + "'s Profile Page");
        controller.setUser(user);
	}
}
