package junit;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

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

    @Before
    public void setup() {
        instance = Model.instance();
        
    }

    @Test
    public void testRegisterUser() {
        String userName  = "User Number: " + Math.random() * Integer.MAX_VALUE;
        while (instance.checkUserExists(userName) == true) {
            userName = "User Number: " + Math.random() * Integer.MAX_VALUE;
        }
        User user = new User(userName, "hello", AuthorizationLevel.USER);
        assertFalse(instance.checkUserExists(user.getName()));
        assertTrue(instance.addUser(user));
        
    }

    @Test
    public void testRegisterSameUser() {
        User userOne = new User("New User", "n", AuthorizationLevel.USER);
        boolean b = instance.addUser(userOne);
        assertTrue(instance.checkUserExists(userOne.getName()));
        assertFalse(instance.addUser(userOne));
    }

}