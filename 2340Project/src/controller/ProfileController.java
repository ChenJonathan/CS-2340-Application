package controller;

import model.User;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Controller for working with the profile page
 * @author Pravan Kalaga
 */
public class ProfileController extends DialogController {
	
	@FXML
	private TextField emailField;
	
	@FXML
	private TextField numberField;
	
	@FXML
	private TextField addressField;
	
	private User user;
	
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
		
		dialogStage.close();
	}
	
	@FXML
	/**
	 * closes the pop up upon pressing cancel
	 */
	public void handleCancelPressed() {
		dialogStage.close();
	}
	
	
	
}
