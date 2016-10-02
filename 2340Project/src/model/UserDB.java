package model;

import java.util.LinkedList;

/**
 * 
 * "Database"ish (basically a collection) of all users.
 * @author Alok Tripathy
 *
 */
public class UserDB {

	private LinkedList<User> _userList; 
	
	/**
	 * No-arg constructor. Just creates a new List.
	 */
	public UserDB() {
		_userList = new LinkedList<>();
	}
	
	/**
	 * Initializes _userList with contents of given list.
	 * @param userList given list to initialize our list with.
	 */
	public UserDB(LinkedList<User> userList) {
		for (User user: userList) {
			_userList.add(user);
		}
	}
	
	/**
	 * getter for our collection of users.
	 * @return userMap.
	 */
	public LinkedList<User> getUsers() {
		return _userList;
	}
	
	/**
	 * Adds a user into the collection of users.
	 * @param user user to add to the list.
	 * @return true if the user was added, false if another user had the same name. 
	 */
	public boolean addUser(User user) {
		for (User u : _userList) {
			boolean namesMatch = u.getName().equals(user.getName());
			if (namesMatch) {
				return false;
			}
		}
		_userList.add(user);

		for (User u : _userList) {
			System.out.println(u.getName() + " " + u.getPassword() + " " + u.getAuth().getName());
		}
		System.out.println();
		return true;
	}
	
	/**
	 * Returns {@code User} if a user exists with the given name and password.
	 * @param name username to search for
	 * @param pass password to search for
	 * @return User with corresponding name and password.
	 */
	public User authenticate(String name, String pass) {
		for (User u : _userList) {
			boolean namesMatch = u.getName().equals(name);
			boolean passwMatch = u.getPassword().equals(pass);
			if (namesMatch && passwMatch) {
				return u;
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
		return authenticate(name, pass) != null;
	}
}
