package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class LoginController {

	@FXML
	private TextField userField;
	
	@FXML
	private PasswordField passwordField;
	
	@FXML
	public void handleOKPressed() {
		String user = userField.getText();
		String passwd = passwordField.getText();
		
		System.out.println("user: " + user);
		System.out.println("passwd: " + passwd);
		if (user.equals("JWASP") && passwd.equals("/etc/passwd")) {
			System.out.println("authenticated");
		} else {
			System.out.println("not authenticated");
		}
	}
}
