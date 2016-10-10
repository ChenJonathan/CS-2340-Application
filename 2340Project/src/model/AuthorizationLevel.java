package model;

/**
 * Created by Wesley on 9/28/16. This enumeration represents all the auth levels
 * a user may hold
 *
 */
public enum AuthorizationLevel {
	USER("User", 0), WORKER("Worker", 1), MANAGER("Manager", 2), ADMIN("Admin", 3);

	/** the full string representation of the auth level */
	private final String name;
	private final int level;

	/**
	 * Constructor for the enumeration
	 *
	 * @param name
	 *            full name of the auth level
	 * @param level
	 *            the numeric level of rights of the user
	 */
	private AuthorizationLevel(String name, int level) {
		this.name = name;
		this.level = level;
	}

	/**
	 *
	 * @return the full class name
	 */
	public String getName() {
		return name;
	}

	/**
	 *
	 * @return the auth level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 *
	 * @return the display string representation of the auth level
	 */
	public String toString() {
		return name;
	}
}
