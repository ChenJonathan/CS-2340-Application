package com.example.pravan.a2340androidapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pravan on 12/5/2016.
 */

public class ListDB {

    private static final ListDB listDB = new ListDB();
    private List<Report> repList = new ArrayList<>();
    private List<User> userList = new ArrayList<>();
    private User currentUser;

    public static ListDB getInstance() {
        return listDB;
    }

    public ListDB() {
        userList.add(new User("user", "1", AuthorizationLevel.USER));
        userList.add(new User("admin", "1,", AuthorizationLevel.ADMIN));

        repList.add(new UserReport("Atlanta",33.7490, -84.3880, "Endless Rain TBH", "user", "Other", "Portable"));
    }

    public boolean authenticateUser(String name, String pass) {
        for (User u: userList) {
            if (u.getName().equals(name) && u.getPassword().equals(pass)) {
                currentUser = u;
                return true;
            }
        }
        return false;
    }
    public boolean verifyUser(String s) {
        for (User u: userList) {
            if (u.getName().equals(s)) {
                return true;
            }
        }
        return false;
    }

    public boolean addUser(User u) {
        if (verifyUser(u.getName())) {
            return false;
        }
        userList.add(u);
        return true;
    }

    public void updateUser(String email, String address, String phoneNumber) {
        currentUser.setEmail(email);
        currentUser.setAddress(address);
        currentUser.setPhoneNumber(phoneNumber);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User u) {
        currentUser = u;
    }

    public void addReport(Report r) {
        repList.add(r);
    }

    public List<Report> getRepList() {
        return repList;
    }




}
