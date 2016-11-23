package junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;



import org.junit.Before;

import org.junit.Test;

import database.Model;

import model.AuthorizationLevel;

import model.User;


/**
 *Testing checkUserExists from database.Model.
 *@author Sathvik Kadaveru
 *
 */
public class CheckUserExistsTests {
	/**An instance of the model class. */
	private Model instance;
	/**The Users to check. */
	private User user1, user2;

	/**
	 * Initializes the instance variables.
	 */
	@Before
	public final void setUp() {
		long timeStamp1 = System.currentTimeMillis();
		long timeStamp2 = timeStamp1 + 5;

		instance = Model.instance();
		user1 = new User("Sathvik "
				+ timeStamp1,
				"johncena",
				AuthorizationLevel.USER);
		user2 = new User("Sathvik "
				+ timeStamp2,
				"johncena",
				AuthorizationLevel.USER);
	}

	/**
	 * A test for checking if the user exists.
	 */
	@Test
	public final void test() {
		assertFalse(instance.checkUserExists(user1.getName()));
		assertFalse(instance.checkUserExists(user2.getName()));

		assertTrue(instance.addUser(user1));

		assertTrue(instance.checkUserExists(user1.getName()));
		assertFalse(instance.checkUserExists(user2.getName()));

	}
}
