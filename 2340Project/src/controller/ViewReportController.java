package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import model.Report;
import netscape.javascript.JSObject;

import java.util.List;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;

import database.Model;

/**
 * A controller that controls the view report window
 * 
 * @author Wesley Cheung
 */
public class ViewReportController extends DialogController implements MapComponentInitializedListener {

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

    @FXML
    private GoogleMapView mapView;

    private GoogleMap map;
    
    private List<Report> reports = Model.instance().getReports();

    /**
     * Initializes the controller class. This method is automatically called
     * after the constructor and after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // set the table view to contain the list of courses from the model
        reportTable.setItems((ObservableList<Report>) reports);

        // Initialize the course table with the two columns.
        reportNumberColumn.setCellValueFactory(
                cellData -> cellData.getValue().getNumber());
        reportAuthorColumn.setCellValueFactory(
                cellData -> cellData.getValue().getAuthor());
        reportLocationColumn.setCellValueFactory(
                cellData -> cellData.getValue().getLocation());

        // Listen for selection changes and show the course student list when
        // changed.
        reportTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue,
                        newValue) -> showReportDetails(newValue));

        mapView.addMapInializedListener(this);
    }

    /**
     * Called when the table selection changes
     * 
     * @param c the course that has been selected in the table
     */
    private void showReportDetails(Report r) {

            attributesList.setItems(r.getAttributes());
            detailsList.setItems(r.getDetails());
            
            LatLong loc = new LatLong(r.getLatitude(), r.getLongitude());
            map.panTo(loc);
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
     * Handler for when the new report button on the view report dialog is
     * clicked.
     */
    @FXML
    public void handleNewReport() {
        dialogStage.close();
        showDialogAndWait("../view/NewReport.fxml", "New Report");
        showDialog("../view/ViewReport.fxml", "View Reports");
    }

    @Override
    public void mapInitialized() {
        MapOptions options = new MapOptions();

        //set up the center location for the map
        LatLong center = new LatLong(33.75, -84.4);

        options.center(center)
                .zoom(9)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .mapType(MapTypeIdEnum.TERRAIN);

        map = mapView.createMap(options);


        /** now we communicate with the model to get all the locations for markers */

        for (Report r: reports) {

            if(r != null) {
            
                MarkerOptions markerOptions = new MarkerOptions();
                LatLong loc = new LatLong(r.getLatitude(), r.getLongitude());
    
                markerOptions.position(loc)
                        .visible(Boolean.TRUE)
                        .title(r.getLocation().get());
    
                Marker marker = new Marker(markerOptions);
    
                map.addUIEventHandler(marker,
                        UIEventType.click,
                        (JSObject obj) -> {
                            InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                            infoWindowOptions.content("<b>"+r.getLocation().get() + "</b><br>" + r.getDescription().get());

                            InfoWindow window = new InfoWindow(infoWindowOptions);
                            window.open(map, marker);
                            showReportDetails(r);
                        });
    
                map.addMarker(marker);
            }
        }
        
    }
}