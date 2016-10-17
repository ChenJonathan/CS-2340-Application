package controller;

import java.util.GregorianCalendar;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.UserReport;
import database.Model;
import fxapp.Main;

/**
 * A controller that controls the new report window
 * @author Sathvik Kadaveru
 */
public class NewReportController extends DialogController {

    @FXML
    private ComboBox<String> waterType;

    @FXML
    private ComboBox<String> waterCond;

    @FXML
    private TextArea description;

    @FXML
    private TextField theLocation;

    /**
     * Initializes combobox fields
     */
    @FXML
    private void initialize() {
        waterType.getItems().addAll("Bottled", "Well", "Stream", "Lake", "Spring", "Other");
        waterCond.getItems().addAll("Portable", "Treatable-Muddy", "Treatable-Clear", "Waste");
    }

    /**
     * Handler for when the cancel button on the new report dialog is clicked.
     */
    @FXML
    public void handleCancel() {
        dialogStage.close();
    }

    /**
     * Handler for when the save button on the new report dialog is clicked.
     */
    @FXML
    public void handleSave() {
        try {
            String loc = theLocation.getText();
            String wt = waterType.getSelectionModel().getSelectedItem().toString();
            String wc = waterCond.getSelectionModel().getSelectedItem().toString();
            String des = description.getText();
            UserReport newReport = new UserReport(loc, des, new GregorianCalendar(), Model.instance().getCurrentUser().getName(), wt, wc);
            Model.instance().addReport(newReport);
            dialogStage.close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(Main.stage());
            alert.setTitle("Fields Incomplete");
            alert.setHeaderText("Fields Incomplete");
            alert.setContentText("One or more field is incomplete");
            alert.showAndWait();
        }
    }
}