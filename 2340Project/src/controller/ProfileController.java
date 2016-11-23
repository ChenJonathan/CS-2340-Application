package controller;

import database.Model;
import fxapp.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.User;

/**
 * Controller for working with the profile page.
 * @author Pravan Kalaga
 */
public class ProfileController extends DialogController {

    /**Instance of the current User. */
    private User user;

    /**Text Field for the name. */
    @FXML
    private Text nameText;

    /**TextField for the email. */
    @FXML
    private TextField emailField;

    /**Text Field for the phone number. */
    @FXML
    private TextField numberField;

    /**Text Field for the address. */
    @FXML
    private TextField addressField;

    /**
     * @param u set the current user
     */
    public final void setUser(final User u) {
        this.user = u;
        nameText.setText(user.getName() + "'s Profile Page");
        emailField.setText(user.getEmail());
        numberField.setText(user.getPhoneNumber());
        addressField.setText(user.getAddress());
    }

    /**
     * @param email set previous email info in emailField from User info
     */
    public final void setEmailField(final String email) {
        emailField.setText(email);
    }

    /**
     *
     * @param numb set previous phone number in
     * 	numberField from User
     */
    public final void setNumberField(final String numb) {
        numberField.setText(numb);
    }

    /**
     * @param address set previous address in addressField from User
     * 	Info.
     */
    public final void setAddressField(final String address) {
        addressField.setText(address);
    }


    /**
     * Sets users email, phone, and address variables
     * to the values in the text fields and closes the dialog
     * upon pressing the save button
     */
    @FXML
    public final void handleSavePressed() {
        String email = emailField.getText();
        String phone = numberField.getText();
        String address = addressField.getText();

        user.setAddress(address);
        user.setEmail(email);
        user.setPhoneNumber(phone);

        if (!Model.instance().updateUser(user)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(Main.stage());
            alert.setTitle("Error updating user");
            alert.setHeaderText("Error updating user");
            alert.setContentText("An error occurred while"
                    + "attempting to update the user");
            alert.show();
        }

        dialogStage.close();
    }


    /**
     * closes the pop up upon pressing cancel.
     */
    @FXML
    public final void handleCancelPressed() {
        dialogStage.close();
    }
}
