package model;

/**
 * This class serves as a Facade into the application model.
 * 
 * @author Alok Tripathy
 *
 */
public class Model {

	/** Set Model up as a singleton design pattern */
	private static final Model instance = new Model();
	public static Model getInstance() { return instance; }
	
	private final UserDB database = new UserDB();
	
	/**
	 * 
	 * @return the {@code UserDB}, a collection of all users.
	 */
	public UserDB getDB() {
		return database;
	}
	
	/**
	 * Add a user into the database.
	 * @param user user to add
	 * @return true if user added, false otherwise.
	 */
	public boolean addUser(User user) {
		return database.addUser(user);
	}
}
