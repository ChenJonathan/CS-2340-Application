package junit;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import database.Model;
import model.User;
import model.AuthorizationLevel;

/**
 * Used to test the UpdateUser method.
 *@author Jonathan Chen
 */

public class UpdateUserTests {
    
    private Model instance;
    private User user;

    @Before
    public void setup() {
        instance = Model.instance();
        int count = 1;
        while(instance.checkUserExists("Update Test User " + count)) {
            count++;
        }
        user = new User("Update Test User " + count, "Test", AuthorizationLevel.USER);
        instance.addUser(user);
        instance.authenticateUser(user.getName(), user.getPassword());
    }

    @Test
    public void testUpdatePass() {
        String temp = user.getPassword();
        String test = "Test password";
        
        // Normal case
        user.setPassword(test);
        assertTrue(instance.updateUser(user));
        assertTrue(instance.authenticateUser(user.getName(), test));
        user = instance.getCurrentUser();
        assertTrue(user.getPassword().equals(test));
        
        // Empty string
        user.setPassword("");
        assertFalse(instance.updateUser(user));
        assertTrue(instance.authenticateUser(user.getName(), test));
        user = instance.getCurrentUser();
        assertTrue(user.getPassword().equals(test));
        
        // Null object
        user.setPassword(null);
        assertFalse(instance.updateUser(user));
        assertTrue(instance.authenticateUser(user.getName(), test));
        user = instance.getCurrentUser();
        assertTrue(user.getPassword().equals(test));
        
        // Reset changes
        user.setPassword(temp);
        assertTrue(instance.updateUser(user));
    }

    @Test
    public void testUpdateAuth() {
        AuthorizationLevel temp = user.getAuth();
        
        // Normal case
        user.setAuth(AuthorizationLevel.WORKER);
        assertTrue(instance.updateUser(user));
        instance.authenticateUser(user.getName(), user.getPassword());
        user = instance.getCurrentUser();
        assertTrue(user.getAuth() == AuthorizationLevel.WORKER);
        
        // Null object
        user.setAuth(null);
        assertFalse(instance.updateUser(user));
        instance.authenticateUser(user.getName(), user.getPassword());
        user = instance.getCurrentUser();
        assertTrue(user.getAuth() == AuthorizationLevel.WORKER);
        
        // Reset changes
        user.setAuth(temp);
        assertTrue(instance.updateUser(user));
    }

    @Test
    public void testUpdateEmail() {
        String temp = user.getEmail();
        String test = "Test email";
        
        // Normal case
        user.setEmail(test);
        assertTrue(instance.updateUser(user));
        instance.authenticateUser(user.getName(), user.getPassword());
        user = instance.getCurrentUser();
        assertTrue(user.getEmail().equals(test));
        
        // Empty string
        user.setEmail("");
        assertFalse(instance.updateUser(user));
        instance.authenticateUser(user.getName(), user.getPassword());
        user = instance.getCurrentUser();
        assertTrue(user.getEmail().equals(test));
        
        // Null object
        user.setEmail(null);
        assertFalse(instance.updateUser(user));
        instance.authenticateUser(user.getName(), user.getPassword());
        user = instance.getCurrentUser();
        assertTrue(user.getEmail().equals(test));
        
        // Reset changes
        user.setEmail(temp);
        assertTrue(instance.updateUser(user));
    }

    @Test
    public void testUpdatePhone() {
        String temp = user.getPhoneNumber();
        String test = "Test phone number";
        
        // Normal case
        user.setPhoneNumber(test);
        assertTrue(instance.updateUser(user));
        instance.authenticateUser(user.getName(), user.getPassword());
        user = instance.getCurrentUser();
        assertTrue(user.getPhoneNumber().equals(test));
        
        // Empty string
        user.setPhoneNumber("");
        assertFalse(instance.updateUser(user));
        instance.authenticateUser(user.getName(), user.getPassword());
        user = instance.getCurrentUser();
        assertTrue(user.getPhoneNumber().equals(test));
        
        // Null object
        user.setPhoneNumber(null);
        assertFalse(instance.updateUser(user));
        instance.authenticateUser(user.getName(), user.getPassword());
        user = instance.getCurrentUser();
        assertTrue(user.getPhoneNumber().equals(test));
        
        // Reset changes
        user.setPhoneNumber(temp);
        assertTrue(instance.updateUser(user));
    }

    @Test
    public void testUpdateAddress() {
        String temp = user.getAddress();
        String test = "Test address";
        
        // Normal case
        user.setAddress(test);
        assertTrue(instance.updateUser(user));
        instance.authenticateUser(user.getName(), user.getPassword());
        user = instance.getCurrentUser();
        assertTrue(user.getAddress().equals(test));
        
        // Empty string
        user.setAddress("");
        assertFalse(instance.updateUser(user));
        instance.authenticateUser(user.getName(), user.getPassword());
        user = instance.getCurrentUser();
        assertTrue(user.getAddress().equals(test));
        
        // Null object
        user.setAddress(null);
        assertFalse(instance.updateUser(user));
        instance.authenticateUser(user.getName(), user.getPassword());
        user = instance.getCurrentUser();
        assertTrue(user.getAddress().equals(test));
        
        // Reset changes
        user.setAddress(temp);
        assertTrue(instance.updateUser(user));
    }
}