package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Report;
import database.Model;

/**
 * A controller that controls the view report window
 * @author Wesley Cheung
 */
public class ViewReportController extends DialogController {

    @FXML
    private ListView<String> attributesList;

    @FXML
    private ListView<String> detailsList;

    @FXML
    private TableView<Report> reportTable;

    @FXML
    private TableColumn<Report, String> reportNumberColumn;

    @FXML
    private TableColumn<Report, String> reportAuthorColumn;

    @FXML
    private TableColumn<Report, String> reportLocationColumn;

    /**
     * Initializes the controller class. This method is automatically called
     * after the constructor and after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // set the table view to contain the list of courses from the model
        reportTable.setItems(Model.instance().getReports());

        // Initialize the course table with the two columns.
        reportNumberColumn.setCellValueFactory(cellData -> cellData.getValue().getNumber());
        reportAuthorColumn.setCellValueFactory(cellData -> cellData.getValue().getAuthor());
        reportLocationColumn.setCellValueFactory(cellData -> cellData.getValue().getLocation());

        // Listen for selection changes and show the course student list when
        // changed.
        reportTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showReportDetails(newValue));
    }

    /**
     * Called when the table selection changes
     * @param c the course that has been selected in the table
     */
    private void showReportDetails(Report r) {
        attributesList.setItems(r.getAttributes());
        detailsList.setItems(r.getDetails());

        Model.instance().setCurrentReport(r);
    }

    /**
     * Handler for when the cancel button on the view report dialog is clicked.
     */
    @FXML
    public void handleClose() {
        dialogStage.close();
    }

    /**
     * Handler for when the new report button on the view report dialog is clicked.
     */
    @FXML
    public void handleNewReport() {
        showDialog("../view/NewReport.fxml", "New Report");
    }
}