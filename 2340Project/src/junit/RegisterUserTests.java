package junit;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

import database.Model;
import model.User;
import model.AuthorizationLevel;
/**
 *Testing registerUser Method
 *@author Pravan Kalaga
 *
 */
public class RegisterUserTests {
    private Model instance;
    private User user;

    @Before
    public void setup() {
        instance = Model.instance();
        String userName  = "User Number: " + Math.random() * Integer.MAX_VALUE;
        while (instance.checkUserExists(userName) == True) {
            userName = "User Number: " + Math.random() * Integer.MAX_VALUE;
        }
        user = new User(userName, "hello", AuthorizationLevel.USER);
    }

    @Test
    public void testRegisterUser() {
        assertFalse(instance.checkUserExists(user.getName()));
        assertTrue(instance.addUser(user));
        
    }

    @Test
    public void testRegisterSameUser() {
        assertTrue(instance.checkUserExists(user.getName()));
        assertFalse(instance.addUser(user));
    }

}