package com.example.pravan.a2340androidapp;


import android.os.Parcel;
import android.os.Parcelable;

import com.example.pravan.a2340androidapp.AuthorizationLevel;

import java.io.Serializable;

/**
 * Created by Wesley on 9/28/16.
 *
 * Represents a single user in the system
 *
 * Information Holder
 */
public class User implements Parcelable{
    /**
     * Properties are a way of binding data under the JavaBeans specification.
     *
     * See the article at:
     * http://docs.oracle.com/javafx/2/binding/
     * jfxpub-binding.htm
     * for more details.
     */
    /**Name of the User. */
    private String _name = "";
    /**Password of the user. */
    private String _password = "";
    /**Authorization Level of the User. */
    private AuthorizationLevel _auth = null;
    /**Email of the User. */
    private String _email = ("N/A");
    /**Number of the User. */
    private String _numb = ("N/A");
    /**Address of the User. */
    private String _address = ("N/A");

    /* **********************
     * Getters and setters for properties
     */

    /**
     * @return user's name.
     */
    public final String getName() {
        return _name;
    }

    /**
     * @param name to set for the User.
     */
    public final void setName(final String name) {
        _name = (name);
    }

    /**
     * @return password of the User.
     */
    public final String getPassword() {
        return _password;
    }

    /**
     * @param password what to set the Users password to.
     */
    public final void setPassword(final String password) {
        _password = (password);
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
        return _email;
    }

    /**
     * @param email sets the users email.
     */
	public final void setEmail(final String email) {
        _email = (email);
    }

    /**
     * @return the users phone number.
     */
	public final String getPhoneNumber() {
        return _numb;
    }

    /**
     * @param numb sets the users phone number.
     */
	public final void setPhoneNumber(final String numb) {
        _numb = (numb);
    }

    /**
     * @return the address of the User.
     */
	public final String getAddress() {
        return _address;
    }

    /**
     * @param address sets the Users address.
     */
	public final void setAddress(final String address) {
        _address = (address);
    }



	/**
     * Make a new user.
     * @param name      the user's name
     * @param password     the user's password
     * @param auth     the user's Authorization Level
     */
    public User(final String name, final String password,
                final AuthorizationLevel auth) {
        setName(name);
        setPassword(password);
        _auth = auth;
    }


    /**
     * No param constructor -- DO NOT CALL NORMALLY.
     * This constructor only for GUI use in edit/new user dialog
     */
    public User() {
        setName("enter new name");
        setPassword("enter new password");
        this._auth = AuthorizationLevel.USER;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_name);
        dest.writeString(_password);
        dest.writeString(_email);
        dest.writeString(_address);
        dest.writeString(_numb);
        dest.writeInt(_auth.getLevel());
    }

    public User(Parcel in) {
        _name = in.readString();
        _password = in.readString();
        _email = in.readString();
        _address = in.readString();
        int level = in.readInt();
        if (level == 0) {
            _auth = AuthorizationLevel.USER;
        } else if (level == 1) {
            _auth = AuthorizationLevel.WORKER;
        } else if (level == 2) {
            _auth = AuthorizationLevel.MANAGER;
        } else {
            _auth = AuthorizationLevel.ADMIN;
        }
    }
}
