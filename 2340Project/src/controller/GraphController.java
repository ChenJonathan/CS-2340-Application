package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import model.Location;

/**
 * Controller for the dialog for generating a history graph.
 * @author Alok Tripathy
 *
 */
public class GraphController extends DialogController {

	@FXML
	private TextField longitudeField;
	
	@FXML
	private TextField latitudeField;
	
	@FXML
	private TextField radiusField;
	
	private Location location;
	
	@FXML
	public void handleOKPressed() {
		String longitudeString = longitudeField.getText();
		String latitudeString = latitudeField.getText();
		String dummyName = "dumbdumbname";
		Location inputLocation = Location.makeFromString(longitudeString + "\t" + latitudeString + "\t" + dummyName);
		
	}
	
}
