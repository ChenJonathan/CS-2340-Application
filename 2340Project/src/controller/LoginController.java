package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import database.Model;
import fxapp.Main;

/**
 * Controller for the Login Dialog.
 * @author Alok Tripathy
 */
public class LoginController extends DialogController {

    /**Where the user enters the userName. */
    @FXML
    private TextField userField;

    /**Where the user enters the password. */
    @FXML
    private PasswordField passwordField;

    /**
     * Checks whether the User exists
     * in the data base and completes
     * the login process from there.
     */
    @FXML
    public final void handleOKPressed() {
        String user = userField.getText();
        String pass = passwordField.getText();

        if (Model.instance().authenticateUser(user, pass)) {
            dialogStage.close();
            showScreen("../view/MainScreen.fxml", "Main screen");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(Main.stage());
            alert.setTitle("User Not Found");
            alert.setHeaderText("User Not Found");
            alert.setContentText("Username or password incorrect");
            alert.show();
        }
    }

    /**
     * Closes the dialogue box upon pressing
     * cancel.
     */
    @FXML
    public final void handleCancelPressed() {
        dialogStage.close();
    }
}
