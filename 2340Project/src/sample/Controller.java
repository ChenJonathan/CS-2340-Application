package sample;

import javafx.fxml.FXML;

public class Controller {
	
	private Main mainApplication;
	
	public void setMainApp(Main main) {
		mainApplication = main;
	}

	@FXML
	public void handleLoginPressed() {
		mainApplication.showLoginDialog();
	}
}
