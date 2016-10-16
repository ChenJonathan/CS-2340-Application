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

    @FXML
    private TextField userField;

    @FXML
    private PasswordField passwordField;

    @FXML
    public void handleOKPressed() {
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

    @FXML
    public void handleCancelPressed() {
        dialogStage.close();
    }
}