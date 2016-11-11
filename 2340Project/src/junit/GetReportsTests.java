package junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import database.Model;
import javafx.collections.ObservableList;
import model.AuthorizationLevel;
import model.Report;
import model.User;
import model.UserReport;
import model.WorkerReport;

/**
 *Testing getReports from database.Model
 *and equals from mode.Report
 *@author Sathvik Kadaveru
 *
 */

public class GetReportsTests {
	private Model instance;
	User user;

	@Before
	public void setUp() {
		instance = Model.instance();
		long timeStamp = System.currentTimeMillis();
		user = new User("Sathvik " + timeStamp, "Test", AuthorizationLevel.USER);
	}
	
	@Test
	public void test() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String timeStamp = dateFormat.format(date);
		
		UserReport report1 = new WorkerReport("Imaginary River", 36.102375, -89.978027,
				"Imaginary River Report #1", timeStamp, user.getName(),
				"Bottle", "Very Dirty", 5000, 201);
		
		UserReport report2 = new WorkerReport("Imaginary River", 36.102375, -89.978027,
				"Imaginary River Report #2", timeStamp, user.getName(),
				"Bottle", "Very Dirty", 5000, 201);
		
		instance.addReport(report1);
		
		ObservableList<Report> reports = instance.getReports();
		
		assertNotNull(reports);
		assertTrue(reports.contains(report1));
		assertFalse(reports.contains(report2));

	}
	
}
