package junit;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.NewReportController;
import database.Model;
import fxapp.Main;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;

/**
 *Testing handleSave method in
 *@author Sathvik Kadaveru
 *
 */
public class SaveReportTests {
	
	private NewReportController nrc;
	
	@Before
	public void setup() {
		nrc = new NewReportController();
	}
	
	/*@BeforeClass
	public void setupfx() {
		 Thread t = new Thread("JavaFX Init Thread") {
	        public void run() {
	            Application.launch(Main.class, new String[0]);
	        }
	    };
	    t.setDaemon(true);
	    t.start();
	}*/
	

}
