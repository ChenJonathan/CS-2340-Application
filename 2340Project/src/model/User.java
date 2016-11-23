package model;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Wesley on 9/28/16.
 *
 * Represents a single user in the system
 *
 * Information Holder
 */
public class User {
    /**
     * Properties are a way of binding data under the JavaBeans specification.
     *
     * See the article at:
     * http://docs.oracle.com/javafx/2/binding/
     * jfxpub-binding.htm
     * for more details.
     */
    /**Name of the User. */
    private final StringProperty _name = new SimpleStringProperty();
    /**Password of the user. */
    private final StringProperty _password = new SimpleStringProperty();
    /**Authorization Level of the User. */
    private AuthorizationLevel _auth = null;
    /**Email of the User. */
    private StringProperty _email = new SimpleStringProperty("N/A");
    /**Number of the User. */
    private StringProperty _numb = new SimpleStringProperty("N/A");
    /**Address of the User. */
    private StringProperty _address = new SimpleStringProperty("N/A");

    /* **********************
     * Getters and setters for properties
     */

    /**
     * @return user's name.
     */
    public final String getName() {
        return _name.get();
    }

    /**
     * @param name to set for the User.
     */
    public final void setName(final String name) {
        _name.set(name);
    }

    /**
     * @return password of the User.
     */
    public final String getPassword() {
        return _password.get();
    }

    /**
     * @param password what to set the Users password to.
     */
    public final void setPassword(final String password) {
        _password.set(password);
    }

    /**
     * @return Authorization Level of the User.
     */
    public final AuthorizationLevel getAuth() {
        return _auth;
    }

    /**
     * @param auth authorization level of the user.
     */
    public final void setAuth(final AuthorizationLevel auth) {
        _auth = auth;
    }

    /**
     * @return the email of the user.
     */
	public final String getEmail() {
        return _email.get();
    }

    /**
     * @param email sets the users email.
     */
	public final void setEmail(final String email) {
        _email.set(email);
    }

    /**
     * @return the users phone number.
     */
	public final String getPhoneNumber() {
        return _numb.get();
    }

    /**
     * @param numb sets the users phone number.
     */
	public final void setPhoneNumber(final String numb) {
        _numb.set(numb);
    }

    /**
     * @return the address of the User.
     */
	public final String getAddress() {
        return _address.get();
    }

    /**
     * @param address sets the Users address.
     */
	public final void setAddress(final String address) {
        _address.set(address);
    }

	/**
     * Make a new user.
     * @param name      the user's name
     * @param password     the user's password
     * @param auth     the user's Authorization Level
     */
    public User(final String name, final String password,
                final AuthorizationLevel auth) {
        _name.set(name);
        _password.set(password);
        _auth = auth;
    }

    /**
     * Make a new user without class standing (old).
     * @param name      the user's name
     * @param password     the user's password
     */
    public User(final String name, final String password) {
        _name.set(name);
        _password.set(password);
        _auth = AuthorizationLevel.USER;
    }

    /**
     * No param constructor -- DO NOT CALL NORMALLY.
     * This constructor only for GUI use in edit/new user dialog
     */
    public User() {
        _name.set("enter new name");
        _password.set("enter new password");
        this._auth = AuthorizationLevel.USER;
    }
}
