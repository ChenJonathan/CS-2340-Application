package junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.WorkerReport;
import model.UserReport;
import model.AuthorizationLevel;
import model.Report;
import model.User;
import database.Model;

/**
 *Testing WorkerReport Method
 *@author Obinna Onyeije
 *
 */

public class WorkerReportTests {
	private Model instance;

	@Before
	public void setUp() {
		instance = Model.instance();
	}

	@Test
	public void test() {
		User user = new User("Obinna Onyeije", "password", AuthorizationLevel.WORKER);
		instance.addUser(user);
		UserReport reportOne = new WorkerReport("Missisippi River", 36.102375, -89.978027,
				"Missisippi River Report #1", "12:39 PM, Sunday November 9", user.getName(),
				"Lake", "Treatable-Clear", 500, 201);
		UserReport reportTwo = new WorkerReport("Missisippi River", 36.102375, -89.978027,
				"Missisippi River Report #2", user.getName(),
				"Lake", "Treatable-Clear", 500, 201);
		UserReport reportThree = new WorkerReport("Missisippi River",
				"Missisippi River Report #3", "12:40 PM, Sunday November 9", user.getName(),
				"Lake", "Treatable-Clear", 500, 201);
		UserReport reportFour = new WorkerReport("Missisippi River",
				"Missisippi River Report #4", user.getName(),
				"Lake", "Treatable-Clear", 500, 201);
		
		assertNotNull(reportOne.getLocation());
		assertNotNull(reportOne.getLatitude());
		assertNotNull(reportOne.getLongitude());
		assertNotNull(reportOne.getDescription());
		assertNotNull(reportOne.getTimestamp());
		assertNotNull(reportOne.getAuthor());
		assertNotNull(((WorkerReport) reportOne).getWaterType());
		assertNotNull(((WorkerReport) reportOne).getWaterCond());
		assertNotNull(((WorkerReport) reportOne).getVirusPPM());
		assertNotNull(((WorkerReport) reportOne).getContaminantPPM());
		
		assertNotNull(reportTwo.getLocation());
		assertNotNull(reportTwo.getLatitude());
		assertNotNull(reportTwo.getLongitude());
		assertNotNull(reportTwo.getDescription());
		assertNotNull(reportTwo.getAuthor());
		assertNotNull(((WorkerReport) reportTwo).getWaterType());
		assertNotNull(((WorkerReport) reportTwo).getWaterCond());
		assertNotNull(((WorkerReport) reportTwo).getVirusPPM());
		assertNotNull(((WorkerReport) reportTwo).getContaminantPPM());
		
		assertNotNull(reportThree.getLocation());
		assertNotNull(reportThree.getDescription());
		assertNotNull(reportThree.getTimestamp());
		assertNotNull(reportThree.getAuthor());
		assertNotNull(((WorkerReport) reportThree).getWaterType());
		assertNotNull(((WorkerReport) reportThree).getWaterCond());
		assertNotNull(((WorkerReport) reportThree).getVirusPPM());
		assertNotNull(((WorkerReport) reportThree).getContaminantPPM());
		
		assertNotNull(reportFour.getLocation());
		assertNotNull(reportFour.getDescription());
		assertNotNull(reportFour.getAuthor());
		assertNotNull(((WorkerReport) reportFour).getWaterType());
		assertNotNull(((WorkerReport) reportFour).getWaterCond());
		assertNotNull(((WorkerReport) reportFour).getVirusPPM());
		assertNotNull(((WorkerReport) reportFour).getContaminantPPM());
		
		assertTrue(instance.addReport(reportOne));
		assertTrue(instance.addReport(reportTwo));
		assertTrue(instance.addReport(reportThree));
		assertTrue(instance.addReport(reportFour));
		
		assertNotNull(instance.getReports());

	}

}
