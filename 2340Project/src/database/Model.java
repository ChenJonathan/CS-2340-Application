package database;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Report;
import model.User;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import fxapp.Main;

/**
 * This class serves as a Facade into the application model.
 * @author Jonathan Chen, Alok Tripathy
 */
public class Model {

	// Set Model up as a singleton design pattern
	private static final Model mainModel = new Model();

	public static Model instance() {
		return mainModel;
	}

	// Remember the currently selected user and report
	private User currentUser;
	private Report currentReport;

	private final ReportDB reportDB = new ReportDB();

	/**
	 * Add a user into the database.
	 * @param user user to add
	 * @return true if user added, false otherwise.
	 */
	public boolean addUser(User user) {
	    try {
            HttpResponse<JsonNode> response = Unirest.post("https://chenjonathan-cs-2340-api.herokuapp.com/register")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .body("name=" + user.getName() + 
                      "&pass=" + user.getPassword() + 
                      "&auth=" + user.getAuth() + 
                      "&email=" + user.getEmail() + 
                      "&phone=" + user.getPhoneNumber() + 
                      "&address=" + user.getAddress())
                .asJson();
            if(response.getStatus() != 201)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(Main.stage());
                alert.setTitle("Error adding user");
                alert.setHeaderText("Error adding user");
                alert.setContentText(response.getStatusText());
                alert.show();
                return false;
            }
            return true;
        } catch (UnirestException e) {
            e.printStackTrace();
            return false;
        }
	}
    
    public boolean checkUserExists(String user) {
        try {
            HttpResponse<JsonNode> response = Unirest.get("https://chenjonathan-cs-2340-api.herokuapp.com/user/" + user)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .asJson();
            return response.getStatus() == 200;
        } catch (UnirestException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean authenticateUser(String user, String pass) {
        try {
            HttpResponse<JsonNode> response = Unirest.post("https://chenjonathan-cs-2340-api.herokuapp.com/login")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .body("name=" + user + "&pass=" + pass)
                .asJson();
            if(response.getStatus() == 200)
            {
                // TODO Set current user from JSON response body
                currentUser = new User(user, pass);
                return true;
            }
            return false;
        } catch (UnirestException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Add a report into the database.
     * @param report report to add
     * @return true if report added, false otherwise.
     */
    public boolean addReport(Report report) {
        report.setNumber(instance().reportDB.getReports().size() + 1);
        return instance().reportDB.addReport(report);
    }
    
    public ObservableList<Report> getReports() {
        return reportDB.getReports();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User u) {
        currentUser = u;
    }

	public Report getCurrentReport() {
		return currentReport;
	}

    public void setCurrentReport(Report r) {
        currentReport = r;
    }
}
