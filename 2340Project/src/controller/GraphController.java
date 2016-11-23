package controller;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.json.JSONException;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;

import database.Model;
import fxapp.Main;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

import javafx.collections.ObservableList;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import model.PPMTypes;
import model.Report;
import model.WorkerReport;
import netscape.javascript.JSObject;

/**
 * Controller for the dialog for generating a history graph.
 * @author Alok Tripathy
 *
 */
public class GraphController extends DialogController
		implements MapComponentInitializedListener {

	/**The FX field that takes a longitude. */
	@FXML
	private TextField longitudeField;

	/**The FX field that takes a latitude. */
	@FXML
	private TextField latitudeField;

	/**The FX field that takes in a radius. */
	@FXML
	private TextField radiusField;

	/**The FXX field that takes in a year. */
	@FXML
	private ComboBox<Integer> yearBox;

	/**The FX ComboBox that contains purity. */
	@FXML
	private ComboBox<String> ppmBox;

	/**The field to create a Line Chart. */
	@FXML
	private LineChart<String, Number> qualityChart;

	/**How the app accesses Google Maps. */
	@FXML
    private GoogleMapView mapView;

	/**The actual map from GoogleMaps. */
    private GoogleMap map;

	/**The latitude taken from latitude field. */
    private double latitude = 0;

	/**The longitude taken from longitudeField. */
    private double longitude = 0;

	/**The object for a google map pin/marker. */
    private Marker graphMarker;

	/**An instance of the model class. */
    private Model model = Model.instance();

	/**
	 * Initializes chart so that it can display
	 * data later.
	 */
	@FXML
	public final void initialize() {
		// Add elements to year's combobox.
		for (int i = 2016; i >= 1900; i--) {
			yearBox.getItems().add(i);
		}

		// Adds elements to ppm combobox.
		for (PPMTypes ppm : PPMTypes.values()) {
			ppmBox.getItems().add(ppm.getType());
		}
		qualityChart.setAnimated(false);

		mapView.addMapInializedListener(this);
	}

	/**
	 * When OK is pressed the graph will
	 * get cleared and display a new graph
	 * depending on the fields you want.
	 */
	@FXML
	public final void handleOKPressed() {
		// Clear the data in the chart in case
		// another option is pressed in the future.
		qualityChart.getData().clear();
		String radiusString = radiusField.getText();
		double radius = 0.0;
		try {
			radius = Double.parseDouble(radiusString);
		} catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(Main.stage());
            alert.setTitle("Invalid radius");
            alert.setHeaderText("Invalid radius");
            alert.setContentText("Invalid radius");
            alert.show();
            return;
		}
		// Will substitute with
		// getNearbyReports(inputLocation) at later date.
		ObservableList<Report> reports = null;
		try {
		    reports = Model.instance().
					getReportsByLocation(longitude,
							latitude, radius);
		} catch (JSONException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(Main.stage());
            alert.setTitle("Location Not Found");
            alert.setHeaderText("Location Not Found");
            alert.setContentText("Location incorrect");
            alert.show();
            return;
		}
		String ppmType = ppmBox.
				getSelectionModel().
				getSelectedItem().toString();
		int queryYear = Integer.parseInt(yearBox.
				getSelectionModel().
				getSelectedItem().toString());

		String[] months = {"JAN", "FEB", "MAR", "APR",
				"MAY", "JUN", "JUL", "AUG",
				"SEPT", "OCT", "NOV", "DEC"};
		qualityChart.setTitle("History Graph");

		XYChart.Series<String, Number> series = new XYChart.Series<>();
		series.setName(ppmType + " PPM");

		for (int i = 0; i < months.length; i++) {
			double ppmSum = 0.0;
			int totalMonthReports = 0;

			for (Report report : reports) {
				if (report instanceof WorkerReport) {
					WorkerReport workerReport = (WorkerReport) report;
					String timestamp = workerReport.getTimestamp().getValue();

					int firstSlashIndex = timestamp.indexOf('/');
					int reportMonth = Integer.
							parseInt(timestamp.substring(0, firstSlashIndex));
					boolean correctMonth = reportMonth == i + 1;

					int lastSlashIndex = timestamp.lastIndexOf('/');
					int reportYear = Integer.
							parseInt(timestamp.substring(lastSlashIndex + 1, lastSlashIndex + 5));
					boolean correctYear = reportYear == queryYear;

					if (!correctMonth || !correctYear) {
						continue;
					}

					if (ppmType.equals("VIRUS")) {
						ppmSum += workerReport.getVirusPPM();
					} else if (ppmType.equals("CONTAMINANT")) {
						ppmSum += workerReport.getContaminantPPM();
					}
					totalMonthReports++;

				}
			}

			double ppmAvg = 0.0;
			if (totalMonthReports > 0) {
				ppmAvg = ppmSum / totalMonthReports;
			}
			series.getData().add(new XYChart.
					Data<>(months[i], ppmAvg));

		}

		qualityChart.getData().add(series);
	}
    /**
     * Handler for when the cancel button on the register dialog is clicked.
     */
    @FXML
    public final void handleCancelPressed() {
        dialogStage.close();
    }

    /**
	 * Create the map.
	 */
    @Override
    public final void mapInitialized() {
        MapOptions options = new MapOptions();

        //set up the center location for the map (atlanta)
        LatLong center = new LatLong(33.75, -84.4);

        options.center(center).zoom(9).
				overviewMapControl(false).
				panControl(false).
				rotateControl(false)
                .scaleControl(false).
				streetViewControl(false).
				zoomControl(false).
				mapType(MapTypeIdEnum.TERRAIN);

        map = mapView.createMap(options);

        map.addUIEventHandler(UIEventType.click, (JSObject obj) -> {

            //clear old marker if need be
            if (graphMarker != null) {
                map.removeMarker(graphMarker);
            }

            MarkerOptions newOptions = new MarkerOptions();

            //get current mouse location
            LatLong ll = new LatLong((JSObject) obj.getMember("latLng"));
            latitude = ll.getLatitude();
            longitude = ll.getLongitude();

            //set marker values for mouse location
            LatLong newLoc = new LatLong(latitude, longitude);
            URL drop = null;
            try {
               drop = new File("drop.png").toURI().toURL();
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            newOptions.
					position(newLoc).
					visible(Boolean.TRUE).
					title("Graph Location");

            graphMarker = new Marker(newOptions);

            //add to map
            map.addMarker(graphMarker);
            map.setZoom(map.getZoom() + 1);
            map.setZoom(map.getZoom() - 1);

        });
        /** now we communicate with
		 * the model to get all the
		 * locations for markers
		 */

        List<Report> reports = model.getReports();

        for (Report r : reports) {

            if (r != null) {

                MarkerOptions markerOptions = new MarkerOptions();
                LatLong loc = new LatLong(r.getLatitude(),
						r.getLongitude());

                markerOptions.position(loc).
						visible(Boolean.TRUE).
						title(r.getLocation().get());

                Marker marker = new Marker(markerOptions);

                map.addUIEventHandler(marker, UIEventType.click, (JSObject obj) -> {
                    InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                    infoWindowOptions.content("<b>"
							+ r.getLocation().get()
							+ "</b><br>"
							+ r.getDescription().get());

                    InfoWindow window = new InfoWindow(infoWindowOptions);
                    window.open(map, marker);
                });

                map.addMarker(marker);
            }
        }

    }
}
