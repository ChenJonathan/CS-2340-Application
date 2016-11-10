package junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
public class SathvikJunitTest {
	private Model instance;
	private User user;

	@Before
	public void setUp() {
		instance = Model.instance();
		user = new User("Sathvik Kadaveru", "johncena", AuthorizationLevel.WORKER);
	}
	
	@Test
	public void test() {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String timestamp = dateFormat.format(date);
		
		UserReport reportOne = new WorkerReport("Potomac River River", 39.111, -77.485,
				"Potomac River Report #1", timestamp, user.getName(),
				"Lake", "Treatable-Clear", 500, 201);
		assertTrue(instance.addReport(reportOne));
		
		ObservableList<Report> reports = instance.getReportsByLocation(39.111, -77.485, 1000);
		assertTrue(reports.contains(reportOne));

	}
	
	

}
