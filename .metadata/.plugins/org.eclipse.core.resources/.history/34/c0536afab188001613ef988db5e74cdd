package model;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Wesley on 9/28/16.
 *
 * Represents a single student in the system
 *
 * Information Holder
 */
public class User {
    /**
     * Properties are a way of binding data under the JavaBeans specification.
     *
     * See the article at: http://docs.oracle.com/javafx/2/binding/jfxpub-binding.htm
     * for more details.
     */
    private final StringProperty _name = new SimpleStringProperty();
    private final StringProperty _password = new SimpleStringProperty();
    private AuthorizationLevel _auth = null;
    
    /* **********************
     * Getters and setters for properties
     */
    public String getName() { return _name.get(); }
    public void setName(String name) { _name.set(name); }

    public String getMajor() {return _password.get(); }
    public void setMajor(String major) { _password.set(major); }

    public AuthorizationLevel getStanding() {return _auth;}
	public void setStanding(AuthorizationLevel standing) {_auth = standing;}
	
	/**
     * Make a new student
     * @param name      the user's name
     * @param password     the user's major
     * @param AuthorizationLevel     the user's Authorization Level
     */
    public User(String name, String major, AuthorizationLevel auth) {
        _name.set(name);
        _password.set(major);
        _auth = auth;
    }
    
    /**
     * Make a new student without class standing (old)
     * @param name      the student's name
     * @param major     the student's major
     */
    public User(String name, String major) {
        _name.set(name);
        _password.set(major);
        _auth = AuthorizationLevel.USER;
    }

    /**
     * No param constructor -- DO NOT CALL NORMALLY
     * This constructor only for GUI use in edit/new student dialog
     */
    public User() {
        _name.set("enter new name");
        _password.set("enter new major");
        this._auth = AuthorizationLevel.USER;
    }

}
