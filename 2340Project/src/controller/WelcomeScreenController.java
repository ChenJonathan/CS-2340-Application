package controller;

import javafx.fxml.FXML;

/**
 * Controls what is brought up when the
 * buttons on the welcome screen is pressed.
 *
 * @author Alok Tripathy.
 */
public class WelcomeScreenController extends Controller {

	/**
	 * Brings up login dialogue if pressed.
	 */
	@FXML
	public final void handleLoginPressed() {
		showDialog("../view/LoginDialog.fxml", "Login");
	}

	/**
	 * Brings up register dialogue if pressed.
	 */
	@FXML
	public final void handleRegisterPressed() {
		showDialog("../view/RegisterDialog.fxml", "Register");
	}
}
