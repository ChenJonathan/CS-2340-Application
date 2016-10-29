package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.AuthorizationLevel;
import model.Report;
import model.UserReport;
import model.WorkerReport;
import netscape.javascript.JSObject;

import java.util.List;

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

/**
 * A controller that controls the new report window
 * @author Sathvik Kadaveru
 */
public class NewReportController extends DialogController implements MapComponentInitializedListener {

    @FXML
    private ComboBox<String> waterType;

    @FXML
    private ComboBox<String> waterCond;

    @FXML
    private TextArea description;

    @FXML
    private TextField theLocation;

    @FXML
    private TextField contaminant;

    @FXML
    private TextField virus;

    @FXML
    private GridPane workerDetails;

    @FXML
    private VBox content;

    @FXML
    private GoogleMapView mapView;

    private GoogleMap map;

    private double latitude = 0;

    private double longitude = 0;

    private Marker newReportMarker;

    private Model model = Model.instance();

    /**
     * Initializes combobox fields
     */
    @FXML
    private void initialize() {
        waterType.getItems().addAll("Bottled", "Well", "Stream", "Lake", "Spring", "Other");
        waterCond.getItems().addAll("Portable", "Treatable-Muddy", "Treatable-Clear", "Waste");

        if (model.getCurrentUser().getAuth() == AuthorizationLevel.USER) {
            content.getChildren().remove(workerDetails);
        }
        mapView.addMapInializedListener(this);

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
            if (loc.isEmpty() || wt.isEmpty() || wc.isEmpty() || des.isEmpty()) {
                throw new Exception();
            }
            UserReport newReport;
            if (model.getCurrentUser().getAuth() == AuthorizationLevel.USER) {
                newReport = new UserReport(loc, latitude, longitude, des, model.getCurrentUser().getName(), wt, wc);
            } else {
                String contam = contaminant.getText();
                String vir = virus.getText();
                double c = Double.parseDouble(contam);
                double v = Double.parseDouble(vir);
                if (c < 0 || v < 0 || c > 1000000 || v > 1000000) {
                    throw new NumberFormatException();
                }
                newReport = new WorkerReport(loc, latitude, longitude, des, model.getCurrentUser().getName(), wt, wc, c,
                        v);
            }
            model.addReport(newReport);
            dialogStage.close();
        } catch (NumberFormatException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(Main.stage());
            alert.setTitle("Invalid Input");
            alert.setHeaderText("PPM error");
            alert.setContentText("PPM must be a valid value between 0 and 1,000,000");
            alert.showAndWait();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(Main.stage());
            alert.setTitle("Fields Incomplete");
            alert.setHeaderText("Fields Incomplete");
            alert.setContentText("One or more field is incomplete");
            alert.showAndWait();
        }
    }

    @Override
    public void mapInitialized() {
        MapOptions options = new MapOptions();

        //set up the center location for the map (atlanta)
        LatLong center = new LatLong(33.75, -84.4);

        options.center(center).zoom(9).overviewMapControl(false).panControl(false).rotateControl(false)
                .scaleControl(false).streetViewControl(false).zoomControl(false).mapType(MapTypeIdEnum.TERRAIN);

        map = mapView.createMap(options);

        map.addUIEventHandler(UIEventType.click, (JSObject obj) -> {

            //clear old marker if need be
            if (newReportMarker != null) {
                map.removeMarker(newReportMarker);
            }

            MarkerOptions newOptions = new MarkerOptions();

            //get current mouse location
            LatLong ll = new LatLong((JSObject) obj.getMember("latLng"));
            latitude = ll.getLatitude();
            longitude = ll.getLongitude();

            //set marker values for mouse location
            LatLong newLoc = new LatLong(latitude, longitude);
            newOptions.position(newLoc).visible(Boolean.TRUE).title("Report Location");
            newReportMarker = new Marker(newOptions);

            //add to map
            map.addMarker(newReportMarker);
            map.setZoom(map.getZoom() + 1);
            map.setZoom(map.getZoom() - 1);

        });

        /** now we communicate with the model to get all the locations for markers */

        List<Report> reports = model.getReports();

        for (Report r : reports) {

            if (r != null) {

                MarkerOptions markerOptions = new MarkerOptions();
                LatLong loc = new LatLong(r.getLatitude(), r.getLongitude());

                markerOptions.position(loc).visible(Boolean.TRUE).title(r.getLocation().get());

                Marker marker = new Marker(markerOptions);

                map.addUIEventHandler(marker, UIEventType.click, (JSObject obj) -> {
                    InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                    infoWindowOptions.content("<b>" + r.getLocation().get() + "</b><br>" + r.getDescription().get());

                    InfoWindow window = new InfoWindow(infoWindowOptions);
                    window.open(map, marker);
                });

                map.addMarker(marker);
            }
        }

    }
}