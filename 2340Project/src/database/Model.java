package database;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.GregorianCalendar;

import org.json.JSONObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.AuthorizationLevel;
import model.Report;
import model.User;
import model.UserReport;
import model.WorkerReport;

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

	/**
	 * Add a user into the database.
	 * @param user user to add
	 * @return true if user added, false otherwise.
	 */
	public boolean addUser(User user) {
	    try {
            HttpResponse<JsonNode> response = Unirest.post("https://chenjonathan-cs-2340-api.herokuapp.com/register")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .body("name=" + URLEncoder.encode(user.getName(), "UTF-8") + 
                      "&pass=" + URLEncoder.encode(user.getPassword(), "UTF-8") + 
                      "&auth=" + user.getAuth() + 
                      "&email=" + URLEncoder.encode(user.getEmail(), "UTF-8") + 
                      "&phone=" + URLEncoder.encode(user.getPhoneNumber(), "UTF-8") + 
                      "&address=" + URLEncoder.encode(user.getAddress(), "UTF-8"))
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
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (UnirestException e) {
            e.printStackTrace();
            return false;
        }
	    return false;
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
                JSONObject json = response.getBody().getObject();
                currentUser = new User(json.getString("name"), json.getString("pass"), AuthorizationLevel.fromString(json.getString("auth")));
                currentUser.setEmail(json.getString("email"));
                currentUser.setNumber(json.getString("phone"));
                currentUser.setAddress(json.getString("address"));
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
    public boolean addReport(UserReport report) {
        try {
            String body = "&location=" + URLEncoder.encode(report.getLocation().get().toString(), "UTF-8") + 
                   "&description=" + URLEncoder.encode(report.getDescription().get(), "UTF-8") + 
                   "&timestamp=" + URLEncoder.encode(report.getTimestamp().get(), "UTF-8") + 
                   "&user=" + URLEncoder.encode(report.getAuthor().get(), "UTF-8") + 
                   "&waterType=" + URLEncoder.encode(report.getWaterType(), "UTF-8") + 
                   "&waterCondition=" + URLEncoder.encode(report.getWaterCond(), "UTF-8");
            if(report instanceof WorkerReport) {
                WorkerReport workerReport = (WorkerReport)report;
                body = "type=Worker" + body + 
                       "&virusPPM=" + workerReport.getVirusPPM() + 
                       "&contaminantPPM=" + workerReport.getContaminantPPM();
            } else {
                body = "type=User" + body;
            }
            HttpResponse<JsonNode> response = Unirest.post("https://chenjonathan-cs-2340-api.herokuapp.com/report/new")
                .header("Content-Type", "application/x-www-form-urlencoded").body(body).asJson();
            if(response.getStatus() != 201)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(Main.stage());
                alert.setTitle("Error adding report");
                alert.setHeaderText("Error adding report");
                alert.setContentText(response.getStatusText());
                alert.show();
                return false;
            }
            return true;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public ObservableList<Report> getReports() {
        ObservableList<Report> reports = FXCollections.observableArrayList();
        try {
            HttpResponse<JsonNode> response = Unirest.get("https://chenjonathan-cs-2340-api.herokuapp.com/report")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .asJson();
            for(Object obj : response.getBody().getArray()) {
                JSONObject json = (JSONObject)obj;
                if(json.getString("type").equals("User")) {
                    UserReport report = new UserReport(json.getString("location"), 
                                                       json.getString("description"), 
                                                       json.getString("timestamp"),
                                                       json.getString("user"), 
                                                       json.getString("waterType"), 
                                                       json.getString("waterCondition"));
                    report.setNumber(json.getInt("number"));
                    reports.add(report);
                } else if(json.getString("type").equals("Worker")) {
                    WorkerReport report = new WorkerReport(json.getString("location"), 
                                                           json.getString("description"), 
                                                           json.getString("timestamp"),
                                                           json.getString("user"), 
                                                           json.getString("waterType"), 
                                                           json.getString("waterCondition"), 
                                                           json.getDouble("virusPPM"), 
                                                           json.getDouble("contaminantPPM"));
                    report.setNumber(json.getInt("number"));
                    reports.add(report);
                }
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return reports;
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
