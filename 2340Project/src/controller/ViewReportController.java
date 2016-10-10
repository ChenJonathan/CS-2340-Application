package controller;

import database.Model;
import database.ReportDB;
import fxapp.Main;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Report;

public class ViewReportController {

	private Stage _dialogStage;

	private Main mainApplication;

	/* references to the widgets in the fxml file */
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
		// Initialize the course table with the two columns.
		reportNumberColumn.setCellValueFactory(cellData -> cellData.getValue().getNumber());
		reportAuthorColumn.setCellValueFactory(cellData -> cellData.getValue().getAuthor());

		// Listen for selection changes and show the course student list when
		// changed.
		reportTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showReportDetails(newValue));
	}

	/**
	 * Called when the table selection changes
	 * 
	 * @param c
	 *            the course that has been selected in the table
	 */
	private void showReportDetails(Report r) {

		detailsList.setItems(r.getDetails());

		Model.getInstance().setCurrentReport(r);
	}

	public void setDialogStage(Stage dialogStage) {
		_dialogStage = dialogStage;

	}

	public void setMainApp(Main main) {
		mainApplication = main;
	}

	@FXML
	public void handleClose() {
		_dialogStage.close();
	}

	@FXML
	public void handleNewReport() {
		mainApplication.showNewReportDialog();
	}

}
