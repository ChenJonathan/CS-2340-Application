package junit;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import database.Model;
import model.User;
import model.AuthorizationLevel;
/**
 *Testing registerUser Method.
 *@author Pravan Kalaga
 *
 */
public class RegisterUserTests {
    /**Instance of the model class. */
    private Model instance;

    /**
     * initializes the instance variables.
     */
    @Before
    public final void setup() {
        instance = Model.instance();
    }

    /**
     * Tests if a user can be registered if the
     * username doesn't already exist.
     */
    @Test
    public final void testRegisterUser() {
        String userName  = "User Number: " + Math.random() * Integer.MAX_VALUE;
        while (instance.checkUserExists(userName)) {
            userName = "User Number: " + Math.random() * Integer.MAX_VALUE;
        }
        User user = new User(userName, "hello", AuthorizationLevel.USER);
        assertFalse(instance.checkUserExists(user.getName()));
        assertTrue(instance.addUser(user));
    }

    /**
     * Tests an invalid registration
     * when the User already exists.
     */
    @Test
    public final void testRegisterSameUser() {
        User userOne = new User("New User", "n", AuthorizationLevel.USER);
        instance.addUser(userOne);
        assertTrue(instance.checkUserExists(userOne.getName()));
        assertFalse(instance.addUser(userOne));
    }

}
