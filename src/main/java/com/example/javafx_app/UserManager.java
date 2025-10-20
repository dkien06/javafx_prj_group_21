package com.example.javafx_app;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private final static UserManager instance = new UserManager();
    private UserManager(){};

    private static User currentUser;
    private static List<User> users = new ArrayList<>();

    public static UserManager getInstance() {
        return instance;
    }
    public User getCurrentUser() {
        return currentUser;
    }
    public List<User> getUserList() {
        return users;
    }
}
