package database;

import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.AuthorizationLevel;
import model.Report;
import model.User;
import model.UserReport;
import model.WorkerReport;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

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
     * @return true if user added, false otherwise
     */
    public boolean addUser(User user) {
        try {
            if(user.getAuth() == null)
                throw new NullPointerException();
            HttpResponse<JsonNode> response = Unirest.post("https://chenjonathan-cs-2340-api.herokuapp.com/register")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .body("name=" + URLEncoder.encode(user.getName(), "UTF-8") + 
                      "&pass=" + URLEncoder.encode(user.getPassword(), "UTF-8") + 
                      "&auth=" + user.getAuth() + 
                      "&email=" + URLEncoder.encode(user.getEmail(), "UTF-8") + 
                      "&phone=" + URLEncoder.encode(user.getPhoneNumber(), "UTF-8") + 
                      "&address=" + URLEncoder.encode(user.getAddress(), "UTF-8"))
                .asJson();
            return response.getStatus() == 201;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Updates a user in the database.
     * @param user user to update
     * @return true if user updated, false otherwise
     */
    public boolean updateUser(User user) {
        try {
            if(user.getAuth() == null)
                throw new NullPointerException();
            HttpResponse<JsonNode> response = Unirest.put("https://chenjonathan-cs-2340-api.herokuapp.com/user/" + user.getName())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .body("name=" + URLEncoder.encode(user.getName(), "UTF-8") + 
                      "&pass=" + URLEncoder.encode(user.getPassword(), "UTF-8") + 
                      "&auth=" + user.getAuth() + 
                      "&email=" + URLEncoder.encode(user.getEmail(), "UTF-8") + 
                      "&phone=" + URLEncoder.encode(user.getPhoneNumber(), "UTF-8") + 
                      "&address=" + URLEncoder.encode(user.getAddress(), "UTF-8"))
                .asJson();
            return response.getStatus() == 201;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Checks if a user is in the database.
     * @param user user to check for
     * @return true if user exists, false otherwise
     */
    public boolean checkUserExists(String user) {
        try {
            HttpResponse<JsonNode> response = Unirest.get("https://chenjonathan-cs-2340-api.herokuapp.com/user/" + user)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .asJson();
            return response.getStatus() == 200;
        } catch (Exception e) {
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
                currentUser.setPhoneNumber(json.getString("phone"));
                currentUser.setAddress(json.getString("address"));
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Add a report into the database.
     * @param report report to add
     * @return true if report added, false otherwise
     */
    public boolean addReport(UserReport report) {
        try {
            String body = "&locationName=" + URLEncoder.encode(report.getLocation().get().toString(), "UTF-8") + 
                          "&locationLatitude=" + report.getLatitude() + 
                          "&locationLongitude=" + report.getLongitude() + 
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
            return response.getStatus() == 201;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves all reports from the database.
     * @return all the reports in the database
     */
    public ObservableList<Report> getReports() {
        ObservableList<Report> reports = FXCollections.observableArrayList();
        try {
            HttpResponse<JsonNode> response = Unirest.get("https://chenjonathan-cs-2340-api.herokuapp.com/report")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .asJson();
            for(Object obj : response.getBody().getArray()) {
                JSONObject json = (JSONObject)obj;
                JSONObject jsonLoc = (JSONObject)json.getJSONObject("location");
                JSONArray jsonCoords = (JSONArray)jsonLoc.getJSONArray("coordinates");
                if(json.getString("type").equals("User")) {
                    UserReport report = new UserReport(jsonLoc.getString("name"), 
                                                       jsonCoords.getDouble(1), 
                                                       jsonCoords.getDouble(0), 
                                                       json.getString("description"), 
                                                       json.getString("timestamp"),
                                                       json.getString("user"), 
                                                       json.getString("waterType"), 
                                                       json.getString("waterCondition"));
                    report.setNumber(json.getInt("number"));
                    reports.add(report);
                } else if(json.getString("type").equals("Worker")) {
                    WorkerReport report = new WorkerReport(jsonLoc.getString("name"), 
                                                           jsonCoords.getDouble(1), 
                                                           jsonCoords.getDouble(0), 
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reports;
    }

    /**
     * Retrieves all reports from the database within a specified distance of a specified point.
     * @param longitude the longitude of the specified point
     * @param latitude the latitude of the specified point
     * @param radius the maximum distance from the points to find reports
     * @return all the reports in the database that are close to a specific location
     */
    public ObservableList<Report> getReportsByLocation(double longitude, double latitude, double radius) {
        ObservableList<Report> reports = FXCollections.observableArrayList();
        try {
            HttpResponse<JsonNode> response = Unirest.get("https://chenjonathan-cs-2340-api.herokuapp.com/report/location?" + 
                    "longitude=" + longitude + "&latitude=" + latitude + "&radius=" + radius)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .asJson();
            System.out.println(response.getBody().toString());
            for(Object obj : response.getBody().getArray()) {
                JSONObject json = ((JSONObject)obj).getJSONObject("obj");
                JSONObject jsonLoc = (JSONObject)json.getJSONObject("location");
                JSONArray jsonCoords = (JSONArray)jsonLoc.getJSONArray("coordinates");
                if(json.getString("type").equals("User")) {
                    UserReport report = new UserReport(jsonLoc.getString("name"), 
                                                       jsonCoords.getDouble(1), 
                                                       jsonCoords.getDouble(0), 
                                                       json.getString("description"), 
                                                       json.getString("timestamp"),
                                                       json.getString("user"), 
                                                       json.getString("waterType"), 
                                                       json.getString("waterCondition"));
                    report.setNumber(json.getInt("number"));
                    reports.add(report);
                } else if(json.getString("type").equals("Worker")) {
                    WorkerReport report = new WorkerReport(jsonLoc.getString("name"), 
                                                           jsonCoords.getDouble(1), 
                                                           jsonCoords.getDouble(0), 
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
        } catch (Exception e) {
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

