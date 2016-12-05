package com.example.pravan.a2340androidapp;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


import com.example.pravan.a2340androidapp.AuthorizationLevel;
import com.example.pravan.a2340androidapp.Report;
import com.example.pravan.a2340androidapp.User;
import com.example.pravan.a2340androidapp.UserReport;
import com.example.pravan.a2340androidapp.WorkerReport;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

/**
 * This class serves as a Facade into the application model.
 * @author Jonathan Chen, Alok Tripathy
 */
public class Model {

	/**Set Model up as a singleton design pattern. */
	private static final Model mainModel = new Model();

    /**
     * Gets an instance of the model.
     * @return an instance of the model class.
     */
	public static Model instance() {
		return mainModel;
	}

	/**Current User. */
	private User currentUser;
    /**Current Report. */
	private Report currentReport;

    /**
     * Add a user into the database.
     * @param user user to add
     * @return true if user added, false otherwise
     */
    public final boolean addUser(final User user) {
        try {
            if (user.getAuth() == null) {
                throw new NullPointerException();
            }
            HttpResponse<JsonNode> response =
                    Unirest.post("https://chenjonathan-cs"
                            + "-2340-api.herokuapp.com/register")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .body("name=" + URLEncoder.encode(user.getName(), "UTF-8")
                        + "&pass="
                        + URLEncoder.encode(user.getPassword(), "UTF-8")
                        + "&auth="
                        + user.getAuth()
                        + "&email="
                        + URLEncoder.encode(user.getEmail(), "UTF-8")
                        + "&phone="
                        + URLEncoder.encode(user.getPhoneNumber(), "UTF-8")
                        + "&address="
                        + URLEncoder.encode(user.getAddress(), "UTF-8"))
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
    public final boolean checkUserExists(final String user) {
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

    /**
     * Decides if the user can login.
     * @param user user that's trying to login
     * @param pass password of user for authentication
     * @return true if user can login, false otherwise.
     */
    public final boolean authenticateUser(final String user,
                                          final String pass) {
        try {
            HttpResponse<JsonNode> response = Unirest.
                    post("https://chenjonathan-cs-2340"
                            + "-api.herokuapp.com/login")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .body("name=" + user + "&pass=" + pass)
                .asJson();
            if (response.getStatus() == 200) {
                JSONObject json = response.getBody().getObject();
                currentUser = new User(json.getString("name"),
                        json.getString("pass"),
                        AuthorizationLevel.
                                fromString(json.getString("auth")));
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
    public final boolean addReport(final UserReport report) {
        try {
            String body = "&locationName="
                    + URLEncoder.encode(report.
                        getLocation().
                        toString(), "UTF-8")
                    + "&locationLatitude="
                    + report.getLatitude()
                    + "&locationLongitude="
                    + report.getLongitude()
                    + "&description="
                    + URLEncoder.encode(report.getDescription(), "UTF-8")
                    + "&timestamp="
                    + URLEncoder.encode(report.getTimestamp(), "UTF-8")
                    + "&user="
                    + URLEncoder.encode(report.getAuthor(), "UTF-8")
                    + "&waterType="
                    + URLEncoder.encode(report.getWaterType(), "UTF-8")
                    + "&waterCondition="
                    + URLEncoder.encode(report.getWaterCond(), "UTF-8");
            if (report instanceof WorkerReport) {
                WorkerReport workerReport = (WorkerReport) report;
                body = "type=Worker"
                        + body
                        + "&virusPPM="
                        + workerReport.getVirusPPM()
                        + "&contaminantPPM="
                        + workerReport.getContaminantPPM();
            } else {
                body = "type=User" + body;
            }
            HttpResponse<JsonNode> response = Unirest.post("https://"
                    + "chenjonathan-cs-2340-api.herokuapp.com/report/new")
                .header("Content-Type",
                        "application/x-www-form-urlencoded").
                            body(body).asJson();
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
    public final boolean updateUser(final User user) {
        try {
            if (user.getAuth() == null) {
                throw new NullPointerException();
            }
            HttpResponse<JsonNode> response = Unirest.
                    put("https://chenjonathan-cs-2340"
                            + "-api.herokuapp.com/user/" + user.getName())
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .body("name=" + URLEncoder.encode(user.getName(), "UTF-8")
                            + "&pass="
                            + URLEncoder.encode(user.getPassword(), "UTF-8")
                            + "&auth="
                            + user.getAuth()
                            + "&email="
                            + URLEncoder.encode(user.getEmail(), "UTF-8")
                            + "&phone="
                            + URLEncoder.encode(user.getPhoneNumber(), "UTF-8")
                            + "&address="
                            + URLEncoder.encode(user.getAddress(), "UTF-8"))
                    .asJson();

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
    public final List<Report> getReports() {
        List<Report> reports = new ArrayList<>();
        try {
            HttpResponse<JsonNode> response = Unirest.
                    get("https://chenjonathan-"
                            + "cs-2340-api.herokuapp.com/report")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .asJson();

            for (int i = 0; i < response.getBody().getArray().length(); i++) {
                JSONObject json = (JSONObject) response.getBody().getArray().get(i);
                JSONObject jsonLoc = (JSONObject) json.
                        getJSONObject("location");
                JSONArray jsonCoords = (JSONArray) jsonLoc.
                        getJSONArray("coordinates");
                if (json.getString("type").equals("User")) {
                    UserReport report = new UserReport(jsonLoc.
                            getString("name"),
                                jsonCoords.getDouble(1),
                                jsonCoords.getDouble(0),
                                json.getString("description"),
                                json.getString("timestamp"),
                                json.getString("user"),
                                json.getString("waterType"),
                                json.getString("waterCondition"));
                    report.setNumber(json.getInt("number"));
                    reports.add(report);
                } else if (json.getString("type").equals("Worker")) {
                    WorkerReport report = new WorkerReport(jsonLoc.
                            getString("name"),
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
     * @return get the current user.
     */
    public final User getCurrentUser() {
        return currentUser;
    }

    /**
     * @param u sets the current user.
     */
    public final void setCurrentUser(final User u) {
        currentUser = u;
    }

    /**
     * @return gets the current report.
     */
	public final Report getCurrentReport() {
		return currentReport;
	}

    /**
     * @param r sets the current report.
     */
    public final void setCurrentReport(final Report r) {
        currentReport = r;
    }
}

