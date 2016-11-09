import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;\

import database.Model;
import model.User;
import model.AuthorizationLevel;
/**
 *Testing registerUser Method
 *@author Pravan Kalaga
 *
 */
public class registerUserTests {
    private Model instance;

    @Before
    public void setup() {
        instance = Model.instance;
    }

    @Test
    public void testRegisterUser() {
        User user = new User("Jonathan THE Chen", "hello", AuthorizationLevel.USER);
        assertFalse(instance.checkUserExists(user));
        assertTrue(instance.addUser(user));
        User userTwo = new User("Jonathan THE Chen", "oiMate", AuthorizationLevel.WORKER);
        assertTrue(instance.checkUserExists(userTwo));
        assertFalse(instance.addUser(user));
        User userThree = null;
        assertFalse(instance.checkUserExists(user));
        assertFalse(instance.addUser(userThree));
    }

}