package controller;

import fxapp.Main;
import model.User;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

/**
 * Controller for working with the profile page
 * when it is brought up from the main page
 * @author Pravan
 *
 */
public class ProfileController {
	
	////////////////Instance Variables//////////////////////////
	@FXML
	private TextField emailField;
	
	@FXML
	private TextField numberField;
	
	@FXML
	private TextField addressField;
	
	@FXML
	private Stage _dialogStage;
	
	@FXML
	private Main mainApp;
	
	private User user;
	
	//////////////////////////////////////////////////////////////
	
	/**
	 * 
	 * @param ds what to set the dialog stage to
	 */
	public void setDialogStage(Stage ds) {
		_dialogStage = ds;	
	}
	
	/**
	 * 
	 * @param main what to set the mainApp to
	 */
	public void setMainApp(Main main) {
		mainApp = main;
	}
	
	/**
	 * 
	 * @param u set the current user
	 */
	public void setUser(User u) {
		user = u;
	}
	
	/**
	 * 
	 * @param email set previous email info in
	 * 	emailField from User info
	 */
	public void setEmailField(String email) {
		emailField.setText(email);
	}
	
	/**
	 * 
	 * @param numb set previous phone number in
	 * 	numberField from User
	 */
	public void setNumberField(String numb) {
		numberField.setText(numb);
	}
	
	/**
	 * 
	 * @param address set previous address in addressField from User
	 * 	Info
	 */
	public void setAddressField(String address) {
		addressField.setText(address);
	}
	
	
	@FXML
	/**
	 * sets users email, phone, and address variables
	 * to the values in the text fields and closes the popup
	 * upon pressing the save button
	 */
	public void handleSavePressed() {
		String email = emailField.getText();
		String phone = numberField.getText();
		String address = addressField.getText();
		
		user.setAddress(address);
		user.setEmail(email);
		user.setNumber(phone);
		
		_dialogStage.close();
	}
	
	@FXML
	/**
	 * closes the pop up upon pressing cancel
	 */
	public void handleCancelPressed() {
		_dialogStage.close();
	}
	
	
	
}
