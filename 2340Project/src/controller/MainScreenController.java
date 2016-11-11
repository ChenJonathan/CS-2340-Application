package controller;

import javafx.fxml.FXML;
import model.User;
import database.Model;

/**
 * A controller that controls the main page window.
 * @author Alok Tripathy
 */
public class MainScreenController extends Controller {

    /**
     * Sends the User back to the welcome screen.
     */
    @FXML
    public final void handleLogoutPressed() {
        showScreen("../view/WelcomeScreen.fxml", "Welcome!");
    }

    /**
     * Opens up the New Report Creation screen.
     */
    @FXML
    public final void handleNewReportCreation() {
        showDialog("../view/NewReport.fxml", "New Report");
    }

    /**
     * Brings up the View Report Dialogue Box.
     */
    @FXML
    public final void handleViewReportPressed() {
        showDialog("../view/ViewReport.fxml", "View Reports");
    }

    /**
     * Brings up the Dialogue box with the history graph.
     */
    @FXML
    public final void handleHistoryGraphPressed() {
    	showDialog("../view/GraphDialog.fxml", "History Graph");
    }

    /**
     * Brings up the profile dialogue box for
     * that particular user.
     */
    public final void handleProfilePressed() {
        User user = Model.instance().getCurrentUser();
        ProfileController controller =
                (ProfileController)
                        showDialog("../view/ProfilePage.fxml",
                                user.getName() + "'s Profile Page");
        controller.setUser(user);
    }
}
