package database;

import javafx.collections.ObservableList;
import model.Report;
import model.User;

/**
 * This class serves as a Facade into the application model.
 * @author Alok Tripathy
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

	private final UserDB userDB = new UserDB();
	private final ReportDB reportDB = new ReportDB();

	/**
	 * Add a user into the database.
	 * @param user user to add
	 * @return true if user added, false otherwise.
	 */
	public boolean addUser(User user) {
		return instance().userDB.addUser(user);
	}
    
    public boolean checkUserExists(String user) {
        return userDB.getUsers().get(user) != null;
    }
    
    public boolean authenticateUser(String user, String pass) {
        return userDB.userExists(user, pass);
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
