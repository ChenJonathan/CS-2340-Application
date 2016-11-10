package junit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import database.Model;
import fxapp.Main;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import model.AuthorizationLevel;
import model.Report;
import model.User;
import model.UserReport;
import model.WorkerReport;

/**
 *Testing handleSave method in
 *@author Sathvik Kadaveru
 *
 */
public class ReportByLocationTests {
	private Model instance;
	private User user;

	@Before
	public void setUp() {
		instance = Model.instance();
		user = new User("Sathvik Kadaveru", "johncena", AuthorizationLevel.USER);
	}
	
	@Test
	public void test() {
		
		instance.addUser(this.user);
		UserReport reportOne = new UserReport("Potomac River", 39.111, -77.485, "Potomac 1", "timestamp",
	             user.getName(), "River", "Very Bad");
		
		assertTrue(instance.addReport(reportOne));
		
		ObservableList<Report> reports = instance.getReportsByLocation(39.111, -77.485, 100);
		assertTrue(reports.contains(reportOne));

	}
	
	

}
