package database;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import model.AuthorizationLevel;
import model.User;

/**
 * 
 * "Database"ish (basically a collection) of all users.
 * @author Alok Tripathy
 *
 */
public class UserDB {

	private Map<String, User> _userList = new HashMap<>();
	private static User _currentUser;
	
	/**
	 * No-arg constructor. Just creates a new List.
	 */
	public UserDB() {
		_userList = new HashMap<>();
		_userList.put("test", new User("test", "test", AuthorizationLevel.USER));
	}
	
	/**
	 * Initializes _userList with contents of given list.
	 * @param userList given list to initialize our list with.
	 */
	public UserDB(Map<String, User> userMap) {
		for (String username: userMap.keySet()) {
			_userList.put(username, userMap.get(username));
		}
	}
	
	/**
	 * getter for our collection of users.
	 * @return userMap.
	 */
	public Map<String, User> getUsers() {
		return _userList;
	}
	
	public static User getCurrentUser() {
		return _currentUser;
	}
	
	/**
	 * Adds a user into the collection of users.
	 * @param user user to add to the list.
	 * @return true if the user was added, false if another user had the same name. 
	 */
	public boolean addUser(User user) {
		/*for (String userName : _userList.keySet()) {
			boolean namesMatch = user.getName().equals(userName);
			if (namesMatch) {
				return false; // username already exists
			}
		}*/
		//
		User existing = _userList.get(user.getName());
		if (existing != null) { //user already exists
			return false;
		}
		//
		_userList.put(user.getName(), user);
		return true;
	}
	
	/**
	 * Returns {@code User} if a user exists with the given name and password.
	 * @param name username to search for
	 * @param pass password to search for
	 * @return User with corresponding name and password.
	 */
	public User authenticate(String name, String pass) {
		for (String userName : _userList.keySet()) {
			boolean namesMatch = name.equals(userName);
			if (namesMatch) {
				if (pass.equals(_userList.get(userName).getPassword())) {
					
					return _userList.get(userName);
				} else {
					return null;
				}
			}
		}
		return null;
	}
	
	/**
	 * Returns true if a user exists with the corresponding username and password.
	 * @param name username to search for.
	 * @param pass password to search for
	 * @return true if user exists, false otherwise.s
	 */
	public boolean userExists(String name, String pass) {
		_currentUser = authenticate(name, pass);
		return _currentUser != null;
	}
}
