package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Report;
import model.User;

/**
 * This class serves as a Facade into the application model.
 * 
 * @author Alok Tripathy
 *
 */
public class Model {

	/** Set Model up as a singleton design pattern */
	private static final Model instance = new Model();

	public static Model getInstance() {
		return instance;
	}

	/** Remember the currently selected report */
	private Report currentReport;

	private final UserDB userDB = new UserDB();
	private final ReportDB reportDB = new ReportDB();

	/**
	 * 
	 * @return the {@code UserDB}, a collection of all users.
	 */
	public UserDB getUsers() {
		return userDB;
	}

	public ReportDB getReports() {
		return reportDB;
	}

	/**
	 * Add a user into the database.
	 * 
	 * @param user
	 *            user to add
	 * @return true if user added, false otherwise.
	 */
	public boolean addUser(User user) {
		return Model.getInstance().getUsers().addUser(user);
	}

	public User getCurrentUser() {
		return Model.getInstance().getCurrentUser();
	}

	public boolean addReport(Report report) {
		return Model.getInstance().getReports().addReport(report);
	}

	public void setCurrentReport(Report r) {
		currentReport = r;
	}

	public Report getCurrentReport() {
		return currentReport;
	}
}
