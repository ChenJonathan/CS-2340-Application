package controller;

import model.User;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * Controller for working with the profile page
 * @author Pravan Kalaga
 */
public class ProfileController extends DialogController {

    private User user;

    @FXML
    private Text nameText;

    @FXML
    private TextField emailField;

    @FXML
    private TextField numberField;

    @FXML
    private TextField addressField;

    /**
     * @param u set the current user
     */
    public void setUser(User user) {
        this.user = user;
        nameText.setText(user.getName() + "'s Profile Page");
        emailField.setText(user.getEmail());
        numberField.setText(user.getPhoneNumber());
        addressField.setText(user.getAddress());
    }

    /**
     * @param email set previous email info in emailField from User info
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
     * Sets users email, phone, and address variables
     * to the values in the text fields and closes the dialog
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