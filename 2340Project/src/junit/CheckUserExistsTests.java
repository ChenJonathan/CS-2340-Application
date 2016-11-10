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
 *Testing checkUserExists from database.Model
 *@author Sathvik Kadaveru
 *
 */
public class CheckUserExistsTests {
	private Model instance;
	private User user1, user2;

	@Before
	public void setUp() {
		long timeStamp1 = System.currentTimeMillis();
		long timeStamp2 = timeStamp1 + 5;
		
		instance = Model.instance();
		user1 = new User("Sathvik " + timeStamp1, "johncena", AuthorizationLevel.USER);
		user2 = new User("Sathvik " + timeStamp2, "johncena", AuthorizationLevel.USER);
	}
	
	@Test
	public void test() {
		assertFalse(instance.checkUserExists(user1.getName()));
		assertFalse(instance.checkUserExists(user2.getName()));
		
		assertTrue(instance.addUser(user1));
		
		assertTrue(instance.checkUserExists(user1.getName()));
		assertFalse(instance.checkUserExists(user2.getName()));

	}
	
	

}
