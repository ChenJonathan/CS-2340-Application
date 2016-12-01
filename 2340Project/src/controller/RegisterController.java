package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.AuthorizationLevel;
import model.User;
import database.Model;
import fxapp.Main;

/**
 * Controller for the Register Dialog.
 * @author Alok Tripathy
 */
public class RegisterController extends DialogController {

    /**Text Field for the user name. */
    @FXML
    private TextField userField;

    /**Text Field for the password. */
    @FXML
    private PasswordField passwordField;

    /**ComboBox for authorization levels. */
    @FXML
    private ComboBox<String> authBox;

    /**Instance of the new User. */
    private User user;

    /**
     * Loads all authorization levels into the authorization ComboBox on launch.
     */
    @FXML
    private void initialize() {
        for (AuthorizationLevel level : AuthorizationLevel.values()) {
            authBox.getItems().add(level.getName());
        }
        user = new User();
    }

    /**
     * Handler for when the OK button on the register dialog is clicked.
     */
    @FXML
    public final void handleOKPressed() {
        if (authBox.getSelectionModel().getSelectedItem() == null || userField.getText().isEmpty()
                || passwordField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(Main.stage());
            alert.setTitle("Error adding user");
            alert.setHeaderText("Error adding user");
            alert.setContentText("Fields not filled");
            alert.show();
            return;
        }
        String auth = authBox.getSelectionModel().getSelectedItem().toString();
        for (AuthorizationLevel authLevel : AuthorizationLevel.values()) {
            if (auth.equals(authLevel.getName())) {
                user.setAuth(authLevel);
                break;
            }
        }

        if (Model.instance().checkUserExists(user.getName())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(Main.stage());
            alert.setTitle("User already exists");
            alert.setHeaderText("User already exists");
            alert.setContentText("A user with this username already exists");
            alert.showAndWait();
        } else if (!Model.instance().addUser(user)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(Main.stage());
            alert.setTitle("Error adding user");
            alert.setHeaderText("Error adding user");
            alert.setContentText("An error occurred while" + "attempting to add the user");
            alert.show();
        }

        user.setName(userField.getText());
        user.setPassword(passwordField.getText());
        dialogStage.close();
    }

    /**
     * Handler for when the cancel button on the register dialog is clicked.
     */
    @FXML
    public final void handleCancelPressed() {
        dialogStage.close();
    }
}
