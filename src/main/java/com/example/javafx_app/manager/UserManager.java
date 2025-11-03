package com.example.javafx_app.manager;

import com.example.javafx_app.Account.Account;
import com.example.javafx_app.User.User;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private final static UserManager instance = new UserManager();
    private UserManager(){}

    private static User currentUser;
    private static final List<User> users = new ArrayList<>();

    public static UserManager getInstance() {
        return instance;
    }
    public User getCurrentUser() {
        return currentUser;
    }
    public List<User> getUserList() {
        return users;
    }

    public void setCurrentUser(User user){
        currentUser = user;
    }
    public void addUser(User user){
        users.add(user);
    }
    public User findUserFromAccount(Account account){
        for(User u : users){
            if(u.getCitizenID().equals(account.getCitizenID())){
                return u;
            }
        }
        return null;
    }
    public User findUserFromEmail(String email){
        for(User u : users){
            if(u.getEmail().equals(email)){
                return u;
            }
        }
        return null;
    }
    public User findUserFromPhoneNumber(String phoneNumber){
        for (User u : users){
            if(u.getPhoneNumber().equals(phoneNumber)){
                return u;
            }
        }
        return null;
    }
}
