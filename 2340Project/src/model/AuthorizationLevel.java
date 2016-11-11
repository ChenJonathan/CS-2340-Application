package model;

/**
 * Created by Wesley on 9/28/16.
 * This enumeration represents all the auth levels a user may hold
 */
public enum AuthorizationLevel {
    /**
     * User level authorization.
     * Lowest Level Authorization
     */
    USER("User", 0),

    /**
     * Worker level authorization.
     */
    WORKER("Worker", 1),

    /**
     * Manager level authorization.
     */
    MANAGER("Manager", 2),

    /**
     * Admin level authorization.
     * Highest Level Authorization
     */
    ADMIN("Admin", 3);

    /** the full string representation of the auth level. */
    private final String name;
    /** An integer indicator of the auth level. */
    private final int level;

    /**
     * Constructor for the enumeration.
     * @param n full name of the auth level
     * @param l the numeric level of rights of the user
     */
    AuthorizationLevel(final String n, final int l) {
        this.name = n;
        this.level = l;
    }

    /**
     *
     * @return the full class name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the auth level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @param string Indicating level of Authorization.
     * @return the auth level representation of the display string
     */
    public static AuthorizationLevel fromString(final String string) {
        for (AuthorizationLevel auth : AuthorizationLevel.values()) {
            if (auth.toString().equals(string)) {
                return auth;
            }
        }
        return null;
    }

    /**
     * @return the display string representation of the auth level
     */
    public String toString() {
        return name;
    }
}
